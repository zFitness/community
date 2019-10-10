package cn.zm.community.community.mapper;

import cn.zm.community.community.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

/**
 * @author zfitness
 */
@Mapper
@Component
public interface UserMapper {
    /**
     * 插入一条数据
     *
     * @param user
     */
    @Insert("insert into user(account_id, name, token, gmt_create, gmt_modified, avatar_url, bio)" +
            " values(#{accountId}, #{name}, #{token}, #{gmtCreate}, #{gmtModified}, #{avatarUrl}, #{bio})")
    void insert(User user);

    /**
     * 根据token查找用户
     *
     * @param token
     * @return
     */
    @Select("select * from user where token = #{token}")
    User findByToken(@Param("token") String token);

    /**
     * 根据id查找
     *
     * @param creator
     * @return
     */
    @Select("select * from user where id = #{id}")
    User findById(@Param("id") Integer creator);

    /**
     * d
     * @param accountId
     * @return
     */
    @Select("select * from user where account_id = #{accountId}")
    User findByAccountId(@Param("accountId") String accountId);

    /**
     * 更新
     * @param user
     */
    @Update("update user set name=#{name},token=#{token},gmt_modified=#{gmtModified},avatar_url=#{avatarUrl},bio=#{bio} where id=#{id}")
    void update(User user);
}
