package cn.zm.community.community.model;

import lombok.Data;

/**
 * @author zfitness
 */
@Data
public class Question {
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

}
