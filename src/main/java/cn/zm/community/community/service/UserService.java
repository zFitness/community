package cn.zm.community.community.service;

import cn.zm.community.community.mapper.UserMapper;
import cn.zm.community.community.model.User;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zfitness
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    /**
     * @param user
     */
    public void createOrUpdate(User user) {
        LambdaQueryWrapper<User> query = Wrappers.lambdaQuery();
        query.eq(User::getAccountId, user.getAccountId());
        User dbUser = userMapper.selectOne(query);
        if (dbUser == null) {
            //插入
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
        } else {
            //更新
            dbUser.setGmtModified(System.currentTimeMillis());
            dbUser.setAvatarUrl(user.getAvatarUrl());
            dbUser.setName(user.getName());
            dbUser.setToken(user.getToken());
            dbUser.setBio(user.getBio());
            userMapper.updateById(dbUser);
        }
    }
}
