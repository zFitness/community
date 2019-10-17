package cn.zm.community.community.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author zfitness
 */
@Data
public class User {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    @TableField("ACCOUNT_ID")
    private String accountId;
    private String token;
    @TableField("GMT_CREATE")
    private Long gmtCreate;
    @TableField("GMT_MODIFIED")
    private Long gmtModified;
    @TableField("avatar_url")
    private String avatarUrl;
    private String bio;
}
