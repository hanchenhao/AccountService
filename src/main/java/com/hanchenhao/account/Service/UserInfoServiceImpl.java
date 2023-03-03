package com.hanchenhao.account.Service;

import com.hanchenhao.account.Converter.Common.UserInfoCommonConverter;
import com.hanchenhao.account.DAO.Implement.UserInfoDAO;
import com.hanchenhao.account.Model.Common.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserInfoServiceImpl implements UserInfoService{

    final private UserInfoDAO userInfoDAO;
    final private UserInfoCommonConverter persistenceDataToCommon;

    @Autowired
    public UserInfoServiceImpl(UserInfoDAO userInfoDAO, UserInfoCommonConverter converter) {
        this.userInfoDAO = userInfoDAO;
        this.persistenceDataToCommon = converter;
    }

    @Override
    public UserInfo getUserInfoById(long id) {
        return persistenceDataToCommon.convert(userInfoDAO.getUserInfoById(id));
    }
}
