package cn.zm.community.community.controller;

import cn.zm.community.community.dto.PaginationDTO;
import cn.zm.community.community.dto.QuestionDTO;
import cn.zm.community.community.mapper.QuestionMapper;
import cn.zm.community.community.mapper.UserMapper;
import cn.zm.community.community.model.Question;
import cn.zm.community.community.model.User;
import cn.zm.community.community.service.QuestionService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Cookie;
import java.util.List;

/**
 * @author zfitness
 */
@Controller
public class IndexController {
    @Autowired
    private QuestionService questionService;

    @GetMapping({"/index", "/"})
    public String hello(HttpServletRequest request,
                        Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "5") Integer size) {


        PaginationDTO pagination = questionService.list(page, size);
        model.addAttribute("pagination", pagination);
        System.out.println(pagination);


        return "index";
    }
}
