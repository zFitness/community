package cn.zm.community.community.dto;

import lombok.Data;

/**
 * 评论传输对象
 *
 * @author zfitness
 */
@Data
public class CommentDTO {
    private Long parentId;
    private String content;
    private Integer type;


}
