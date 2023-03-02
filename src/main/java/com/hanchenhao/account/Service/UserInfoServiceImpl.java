package com.hanchenhao.account.Service;

import com.hanchenhao.account.Converter.Service.UserInfoServiceConverter;
import com.hanchenhao.account.DAO.Implement.UserInfoDAO;
import com.hanchenhao.account.Model.Service.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserInfoServiceImpl implements UserInfoService{

    final private UserInfoDAO userInfoDAO;
    final private UserInfoServiceConverter converter;

    @Autowired
    public UserInfoServiceImpl(UserInfoDAO userInfoDAO, UserInfoServiceConverter converter) {
        this.userInfoDAO = userInfoDAO;
        this.converter = converter;
    }


    @Override
    public UserInfo getUserInfoById(String id) {
        return null;
    }
}
