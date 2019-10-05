package cn.zm.community.community.controller;

import cn.zm.community.community.dto.QuestionDTO;
import cn.zm.community.community.mapper.QuestionMapper;
import cn.zm.community.community.mapper.UserMapper;
import cn.zm.community.community.model.Question;
import cn.zm.community.community.model.User;
import cn.zm.community.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author zfitness
 */
@Controller
public class IndexController {
    @Autowired
    private UserMapper mapper;
    @Autowired
    private QuestionService questionService;

    @GetMapping({"/index", "/"})
    public String hello(HttpServletRequest request,
                        Model model) {
        Cookie[] cookies = request.getCookies();
        String token = null;
        User user = null;
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

        List<QuestionDTO> questions = questionService.findAll();
        for (QuestionDTO question : questions) {
            System.out.println("change" + question);
        }
        model.addAttribute("questions", questions);
        System.out.println(questions);
        return "index";
    }
}
