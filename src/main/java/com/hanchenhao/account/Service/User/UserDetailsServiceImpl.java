package com.hanchenhao.account.Service.User;

import com.hanchenhao.account.DAO.Implement.UserInfoDAO;
import com.hanchenhao.account.Exception.InvalidParamException;
import com.hanchenhao.account.Model.Persistence.UserInfo;
import com.hanchenhao.account.Security.LoginDetails.LoginUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Objects;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserInfoDAO dao;
    private final RedisTemplate<String, Object> redisTemplate;


    @Autowired
    public UserDetailsServiceImpl(UserInfoDAO dao, RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.dao = dao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //TODO Redis读取用户信息
        LoginUserDetails loginUserDetails = (LoginUserDetails) redisTemplate.opsForValue().get("UserDetails:" + username);
        //TODO Redis读取用户信息失败，数据库查询
        if (Objects.isNull(loginUserDetails)) {
            UserInfo info = dao.getUserInfoByUserName(username);
            LoginUserDetails user = LoginUserDetails.builder()
                    .permissions(info.getPerms())
                    .userInfo(info).build();
            if (Objects.isNull(user)) {
                throw new InvalidParamException("用户数据库信息查询错误");
            }
            //TODO 数据库查询成功，Redis存用户信息
            redisTemplate.opsForValue().set("UserDetails:" + username, user);
            return user;

        }

        return loginUserDetails;
    }
}
