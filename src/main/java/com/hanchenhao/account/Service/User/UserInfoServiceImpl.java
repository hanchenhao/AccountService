package com.hanchenhao.account.Service.User;

import com.hanchenhao.account.Converter.Common.UserInfoCommonConverter;
import com.hanchenhao.account.DAO.Implement.UserInfoDAO;
import com.hanchenhao.account.Model.Common.UserInfo;
import com.hanchenhao.account.Security.Utiils.JwtUtils;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Component
public class UserInfoServiceImpl implements UserInfoService {

    final private UserInfoDAO userInfoDAO;
    final private UserInfoCommonConverter persistenceDataToCommon;
    final private AuthenticationManager authenticationManager;

    private final RedisTemplate<String, Object> redisTemplate;


    @Autowired
    public UserInfoServiceImpl(UserInfoDAO userInfoDAO,
                               UserInfoCommonConverter converter,
                               AuthenticationManager authenticationManager,
                               RedisTemplate<String, Object> redisTemplate) {
        this.userInfoDAO = userInfoDAO;
        this.persistenceDataToCommon = converter;
        this.authenticationManager = authenticationManager;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public UserInfo getUserInfoById(long id) {
        return persistenceDataToCommon.convert(userInfoDAO.getUserInfoById(id));
    }

    @Override
    public UserInfo getUserInfoByUserName(String name) {
        return persistenceDataToCommon.convert(userInfoDAO.getUserInfoByUserName(name));
    }

    @Override
    public int userInfoRegister(String name, String password) {
        String salt = UUID.randomUUID().toString();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String pwd = bCryptPasswordEncoder.encode(password);
        var user = com.hanchenhao.account.Model.Persistence.UserInfo
                .builder()
                .userName(name)
                .password(pwd)
                .salt(salt)
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now()).build();
        return userInfoDAO.userInfoRegister(user);
    }

    @Override
    public ResponseEntity login(UserInfo userInfo) {
        var token = new UsernamePasswordAuthenticationToken(userInfo.getUserName(), userInfo.getPassword());
        Authentication authenticate = authenticationManager.authenticate(token);
        if (authenticate.isAuthenticated()) {
            //生成jwt
            String jwt = JwtUtils.createJwt(String.valueOf(userInfo.getUserName()), 60);
            // redis 存储jwt
            redisTemplate.opsForValue().set("jwt:" + userInfo.getUserName(), jwt);
            // 返回responsebody数据
            return ResponseEntity.ok(jwt);
        }
        return ResponseEntity.ok("参数错误");
    }

    @Override
    public String logout() {
        //todo 删除Redis内的jwt token
        val principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (Objects.isNull(principal)){
            throw new RuntimeException("没有查询到用户信息");
        }
        redisTemplate.delete("jwt:" + principal);
        redisTemplate.delete("UserDetails:" + principal);
        return principal.toString() + ": 退出登录成功";
    }
}
