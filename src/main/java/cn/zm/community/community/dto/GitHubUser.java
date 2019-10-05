package cn.zm.community.community.dto;

/**
 * GitHubUser 的实体类
 *
 * @author zfitness
 */
public class GitHubUser {
    private String name;
    private Long id;
    private String bio;
    private String avatarUrl;

    public String getAvatarUrl() {
        return avatarUrl;
    }

    @Override
    public String toString() {
        return "GitHubUser{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", bio='" + bio + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                '}';
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getLogin() {
        return name;
    }

    public void setLogin(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }


}
