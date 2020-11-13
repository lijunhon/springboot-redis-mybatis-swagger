package org.springbootmybaitsredis.redis1.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springbootmybaitsredis.redis1.entity.User;
import org.springbootmybaitsredis.redis1.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    //日志对象
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    //键
    private static final String CACHE_KEY_USER = "user:";

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    //创建用户
    public void createUser(User obj){
        //存进去
        this.userMapper.insertSelective(obj);
        //拿到key
        String key = CACHE_KEY_USER + obj.getId();
        //拿到对象
        obj = this.userMapper.selectByPrimaryKey(obj.getId());
        //缓存进redis
        redisTemplate.opsForValue().set(key,obj);
    }

    //修改用户
    public void updateUser(User obj){

        //存进去
        this.userMapper.updateByPrimaryKeySelective(obj);
        //拿到key
        String key = CACHE_KEY_USER + obj.getId();
        //拿到对象
        obj = this.userMapper.selectByPrimaryKey(obj.getId());
        //缓存进redis
        redisTemplate.opsForValue().set(key,obj);

    }

    //删除用户
    public void deleteUser(Integer userId){
        //让redis缓存失效
        ValueOperations<String ,User> valueOperations = redisTemplate.opsForValue();
        User user = valueOperations.get(CACHE_KEY_USER+userId);
        if(user != null){
            redisTemplate.delete(CACHE_KEY_USER+userId);
        }
        this.userMapper.deleteByPrimaryKey(userId);
    }

    //查询用户
    public User findUser(Integer userId){
        //先查询redis
        ValueOperations<String ,User> valueOperations = redisTemplate.opsForValue();
        User user = valueOperations.get(CACHE_KEY_USER+userId);
        if(user == null){
            user = this.userMapper.selectByPrimaryKey(userId);

            //更新redis
            valueOperations.set(CACHE_KEY_USER+userId, user);
        }

        return user;
    }

}
