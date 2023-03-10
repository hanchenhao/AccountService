package com.hanchenhao.account.Service.User;

import com.hanchenhao.account.DAO.Implement.UserInfoDAO;
import com.hanchenhao.account.Exception.InvalidParamException;
import com.hanchenhao.account.Model.Persistence.UserInfo;
import com.hanchenhao.account.Security.Login.LoginUser;
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
        UserInfo info = dao.getUserInfoByUserName(username);
        LoginUser user = LoginUser.builder().userInfo(info).build();
        if (Objects.isNull(user)) {
            throw new InvalidParamException("用户信息查询错误");
        }

        return user;
    }
}
