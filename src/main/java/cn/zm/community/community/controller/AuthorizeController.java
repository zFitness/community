package cn.zm.community.community.controller;

import cn.zm.community.community.dto.AccessTokenDTO;
import cn.zm.community.community.dto.GitHubUser;
import cn.zm.community.community.provider.GitHubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/callback")
    public String Callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state) {
        AccessTokenDTO dto = new AccessTokenDTO();
        dto.setCode(code);
        dto.setRedirect_uri(redirectURI);
        dto.setState(state);
        dto.setClient_id(clientId);
        dto.setClient_secret(clientSecret);
        String accessToken = provider.getAccessToken(dto);

        if (accessToken != null) {
            GitHubUser user = provider.getUser(accessToken);
            System.out.println(user);
        }

        return "index";
    }
}
