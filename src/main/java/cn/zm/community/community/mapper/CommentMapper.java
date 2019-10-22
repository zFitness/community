package cn.zm.community.community.mapper;

import cn.zm.community.community.model.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @author zfitness
 */
@Mapper
@Component
public interface CommentMapper extends BaseMapper<Comment> {
}
