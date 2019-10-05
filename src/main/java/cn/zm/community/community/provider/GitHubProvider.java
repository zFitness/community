package cn.zm.community.community.provider;

import cn.zm.community.community.dto.AccessTokenDTO;
import cn.zm.community.community.dto.GitHubUser;
import com.alibaba.fastjson.JSON;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author zfitness
 * Component: 加载进spring的上下文
 */
@Component
public class GitHubProvider {
    /**
     * 获取 AccessToken
     * @param dto
     * @return
     */
    public String getAccessToken(AccessTokenDTO dto) {
        MediaType mediaType
                = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(dto));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            System.out.println(string);
            string = string.split("&")[0].split("=")[1];
            return string;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }

    public GitHubUser getUser(String accessToken) {
        OkHttpClient client = new OkHttpClient();


        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token=" + accessToken)
                .build();

        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            System.out.println(string);
            GitHubUser gitHubUser = JSON.parseObject(string, GitHubUser.class);
            System.out.println(gitHubUser);
            return gitHubUser;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
