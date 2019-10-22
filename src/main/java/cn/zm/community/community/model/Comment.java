package cn.zm.community.community.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author zfitness
 */
@Data
public class Comment {
    @TableId(type = IdType.AUTO)
    private Long id;
    @TableField("parent_id")
    private Long parentId;
    private Integer type;
    private Integer commentator;
    @TableField("gmt_create")
    private Long gmtCreate;
    @TableField("gmt_modified")
    private Long gmtModified;
    @TableField("like_count")
    private Long likeCount;
    private String content;
}
