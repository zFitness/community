package cn.zm.community.community.controller;

import cn.zm.community.community.dto.AccessTokenDTO;
import cn.zm.community.community.dto.GitHubUser;
import cn.zm.community.community.mapper.UserMapper;
import cn.zm.community.community.model.User;
import cn.zm.community.community.provider.GitHubProvider;
import cn.zm.community.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @author zfitness
 */
@Controller
public class AuthorizeController {
    @Autowired
    private GitHubProvider provider;
    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.uri}")
    private String redirectURI;
    @Autowired
    private UserService userService;
    /**
     * 管理用户的Mapper
     */
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/callback")
    public String Callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request,
                           HttpServletResponse response
    ) {
        AccessTokenDTO dto = new AccessTokenDTO();
        dto.setCode(code);
        dto.setRedirect_uri(redirectURI);
        dto.setState(state);
        dto.setClient_id(clientId);
        dto.setClient_secret(clientSecret);
        String accessToken = provider.getAccessToken(dto);

        if (accessToken != null) {
            GitHubUser gitHubUser = provider.getUser(accessToken);

            if (gitHubUser != null) {
                User user = new User();
                String token = UUID.randomUUID().toString();
                user.setToken(token);
                user.setName(gitHubUser.getLogin());
                user.setAccountId(String.valueOf(gitHubUser.getId()));

                user.setAvatarUrl(gitHubUser.getAvatarUrl());
                user.setBio(gitHubUser.getBio());
                System.out.println(user);

                userService.createOrUpdate(user);

                Cookie cookie = new Cookie("token", token);
                cookie.setMaxAge(60 * 60 * 24);
                response.addCookie(cookie);
                // 登录成功，
                return "redirect:/";
            } else {
                //登录失败
                return "redirect:/";
            }
        }

        return "index";
    }

    //@RequestMapping(value = "/logout", method = RequestMethod.GET)
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute("user");
        Cookie cookie = new Cookie("token", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        return "redirect:/";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
