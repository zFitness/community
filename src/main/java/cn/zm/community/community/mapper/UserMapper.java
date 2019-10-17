package cn.zm.community.community.mapper;

import cn.zm.community.community.model.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

/**
 * UserMapper
 * @author zfitness
 */
@Mapper
@Component
public interface UserMapper extends BaseMapper<User> {

}
