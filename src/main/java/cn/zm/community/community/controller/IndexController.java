package cn.zm.community.community.controller;

import cn.zm.community.community.mapper.UserMapper;
import cn.zm.community.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author zfitness
 */
@Controller
public class IndexController {
    @Autowired
    private UserMapper mapper;

    @GetMapping({"/index", "/"})
    public String hello(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String token = null;
        User user = null;
        for (Cookie cookie : cookies) {
            if ("token".equals(cookie.getName())) {
                token = cookie.getValue();
                user =  mapper.findByToken(token);
                if (user != null)  {
                    request.getSession().setAttribute("user", user);
                }
                break;
            }
        }

        return "index";
    }
}
