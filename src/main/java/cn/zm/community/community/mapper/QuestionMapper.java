package cn.zm.community.community.mapper;

import cn.zm.community.community.model.Question;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

/**
 * @author zfitness
 */
@Mapper
@Component
public interface QuestionMapper extends BaseMapper<Question> {
    /**
     * 阅读数加1
     * @param id
     */
    @Update("update question set view_count = view_count + 1 where id = ${id}")
    void incView(@Param("id") Integer id);
}
