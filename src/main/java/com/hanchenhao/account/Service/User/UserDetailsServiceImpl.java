package com.hanchenhao.account.Service.User;

import com.hanchenhao.account.DAO.Implement.UserInfoDAO;
import com.hanchenhao.account.Exception.InvalidParamException;
import com.hanchenhao.account.Model.Persistence.UserInfo;
import com.hanchenhao.account.Security.LoginDetails.LoginUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserInfoDAO dao;

    @Autowired
    public UserDetailsServiceImpl(UserInfoDAO dao) {
        this.dao = dao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //TODO Redis读取用户信息
        //TODO Redis读取用户信息失败，数据库查询
        UserInfo info = dao.getUserInfoByUserName(username);
        LoginUserDetails user = LoginUserDetails.builder()
                .permissions(info.getPerms())
                .userInfo(info).build();
        if (Objects.isNull(user)) {
            throw new InvalidParamException("用户信息查询错误");
        }

        //TODO 数据库查询成功，Redis存用户信息
        return user;
    }
}
