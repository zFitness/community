package cn.zm.community.community.dto;

import cn.zm.community.community.model.User;
import lombok.Data;

/**
 * 问题传输对象
 *
 * @author zfitness
 */
@Data
public class QuestionDTO {
    private Integer id;
    private String title;
    private String description;
    private String tag;
    private Long gmtModified;
    private Long gmtCreate;
    private Integer creator;
    private Integer viewCount;
    private Integer commentCount;
    private Integer likeCount;
    private User user;
}
