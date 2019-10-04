package cn.zm.community.community.mapper;

import cn.zm.community.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author zfitness
 */
@Mapper
public interface UserMapper {
    /**
     * 插入一条数据
     *
     * @param user
     */
    @Insert("insert into user(account_id, name, token, gmt_create, gmt_modified)" +
            " values(#{accountId}, #{name}, #{token}, #{gmtCreate}, #{gmtModified})")
    void insert(User user);

    /**
     * 根据token查找用户
     *
     * @param token
     * @return
     */
    @Select("select * from user where token = #{token}")
    User findByToken(@Param("token") String token);
}
