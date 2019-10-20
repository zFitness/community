package cn.zm.community.community.controller;

import cn.zm.community.community.model.User;
import cn.zm.community.community.dto.QuestionDTO;
import cn.zm.community.community.model.Question;
import cn.zm.community.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 发布问题
 *
 * @author zfitness
 */
@Controller
public class PublishController {
    @Autowired
    private QuestionService questionService;

    /**
     * 编辑问题， 把问题的数据填充到 publish.html 里面
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/publish/{id}")
    public String edit(@PathVariable(name = "id") Integer id, Model model) {
        QuestionDTO question = questionService.getById(id);
        model.addAttribute("title", question.getTitle());
        model.addAttribute("description", question.getDescription());
        model.addAttribute("tag", question.getTag());
        model.addAttribute("id", question.getId());
        return "/publish";
    }

    /**
     * get请求发布问题
     * @return
     */
    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }

    /**
     * 提交问题或者更新问题
     * post 请求提交问题
     * @param title
     * @param description
     * @param tag
     * @param request
     * @param model
     * @return
     */
    @PostMapping("/publish")
    private String doPublish(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("tag") String tag,
            @RequestParam(value = "id", required = false) Integer id,
            HttpServletRequest request,
            Model model
    ) {
        //页面VIEW模型
        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);
        model.addAttribute("error", "用户未登录");
        //服务端数据校验
        if (title == null || title == "") {
            model.addAttribute("error", "标题不能为空");
            return "publish";
        }
        if (description == null || description == "") {
            model.addAttribute("error", "问题不能为空");
            return "publish";
        }
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "publish";
        }
        //创建question对象
        Question question = new Question();
        question.setTag(tag);
        question.setTitle(title);
        question.setDescription(description);
        question.setCreator(user.getId());
        question.setId(id);
        System.out.println();
        System.out.println(question);
        System.out.println();
        questionService.createOrUpdate(question);


        return "redirect:/";
    }
}
