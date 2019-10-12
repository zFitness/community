package cn.zm.community.community.mapper;

import cn.zm.community.community.dto.QuestionDTO;
import cn.zm.community.community.model.Question;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zfitness
 */
@Mapper
@Component
public interface QuestionMapper {
    /**
     * 创建一个问题
     *
     * @param question
     */
    @Insert("insert into question(title, description, gmt_modified,gmt_create,creator,tag) " +
            "values(#{title}, #{description}, #{gmtModified}, #{gmtCreate},#{creator}, #{tag})")
    public void create(Question question);

    /**
     * 查询所有问题
     *
     * @return
     */
    @Select("select * from question")
    List<Question> findAll();

    /**
     * 分页查询
     *
     * @param offset
     * @param size
     * @return
     */
    @Select("select * from question limit #{offset}, #{size}")
    List<Question> list(@Param("offset") Integer offset, @Param("size") Integer size);

    /**
     * 查询总数
     *
     * @return
     */
    @Select("select count(1) from question")
    Integer count();

    /**
     * 根据提问
     *
     * @param userId
     * @param offset
     * @param size
     * @return
     */
    @Select("select * from question where creator = ${userId} limit #{offset}, #{size}")
    List<Question> listByUserId(@Param("userId") Integer userId, @Param("offset") Integer offset, @Param("size") Integer size);

    /**
     * 查询
     *
     * @param userId
     * @return
     */
    @Select("select count(1) from question where creator = ${userId}")
    Integer countByUserId(@Param("userId") Integer userId);

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    @Select("select * from question where id = ${id}")
    Question findById(@Param("id") Integer id);

    /**
     * 根据id更新问题
     * @param question
     */
    @Update("update question set title=#{title}, description=#{description}, gmt_modified=#{gmtModified}, tag=#{tag} where id = #{id}")
    void update(Question question);
}
