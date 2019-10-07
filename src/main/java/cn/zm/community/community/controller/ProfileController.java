package cn.zm.community.community.controller;

import cn.zm.community.community.dto.PaginationDTO;
import cn.zm.community.community.mapper.UserMapper;
import cn.zm.community.community.model.User;
import cn.zm.community.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author zfitness
 */
@Controller
public class ProfileController {
    @Autowired
    private UserMapper mapper;
    @Autowired
    private QuestionService questionService;

    @GetMapping("/profile/{action}")
    public String profile(@PathVariable String action,
                          Model model,
                          HttpServletRequest request,
                          @RequestParam(name = "page", defaultValue = "1") Integer page,
                          @RequestParam(name = "size", defaultValue = "5") Integer size) {
        Cookie[] cookies = request.getCookies();
        String token = null;
        User user = null;
        if (cookies != null && cookies.length != 0) {
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())) {
                    token = cookie.getValue();
                    user = mapper.findByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }

        if (user == null) {
            return "redirect:/";
        }
        if ("question".equals(action)) {
            model.addAttribute("section", "question");
            model.addAttribute("sectionName", "我的提问");

        }
        if ("replies".equals(action)) {
            model.addAttribute("section", "replies");
            model.addAttribute("sectionName", "回复");

        }

        PaginationDTO pagination = questionService.list(user.getId(), page, size);
        model.addAttribute("pagination", pagination);
        return "profile";
    }
}
