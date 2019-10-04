package cn.zm.community.community.mapper;

import cn.zm.community.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zfitness
 */
@Mapper
public interface QuestionMapper {
    /**
     * 创建一个问题
     * @param question
     */
    @Insert("insert into question(title, description, gmt_modified,gmt_create,creator,tag) " +
            "values(#{title}, #{description}, #{gmtModified}, #{gmtCreate},#{creator}, #{tag})")
    public void create(Question question);
}
