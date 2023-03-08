package com.hanchenhao.account.Service;

import com.hanchenhao.account.Converter.Common.UserInfoCommonConverter;
import com.hanchenhao.account.DAO.Implement.UserInfoDAO;
import com.hanchenhao.account.Model.Common.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class UserInfoServiceImpl implements UserInfoService {

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

    @Override
    public UserInfo getUserInfoByUserName(String name) {
        return persistenceDataToCommon.convert(userInfoDAO.getUserInfoByUserName(name));
    }

//    @Override
//    public int userInfoRegister(String name, String password) {
//        String salt = UUID.randomUUID().toString();
//        Sha256Hash sha256HashPassword = new Sha256Hash(password, salt);
//        var user = com.hanchenhao.account.Model.Persistence.UserInfo
//                .builder()
//                .userName(name)
//                .password(sha256HashPassword.toBase64())
//                .salt(salt)
//                .createTime(LocalDateTime.now())
//                .updateTime(LocalDateTime.now()).build();
//        return userInfoDAO.userInfoRegister(user);
//    }
}
