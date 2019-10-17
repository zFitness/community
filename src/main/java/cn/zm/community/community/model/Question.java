package cn.zm.community.community.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author zfitness
 */
@Data
public class Question {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String title;
    private String description;
    private String tag;
    @TableField("gmt_create")
    private Long gmtModified;
    @TableField("gmt_modified")
    private Long gmtCreate;
    private Integer creator;
    @TableField("view_count")
    private Integer viewCount;
    @TableField("comment_count")
    private Integer commentCount;
    @TableField("like_count")
    private Integer likeCount;

}
