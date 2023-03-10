package com.hanchenhao.account.Service.User;

import com.hanchenhao.account.Model.Common.UserInfo;
import org.springframework.http.ResponseEntity;


public interface UserInfoService {
    UserInfo getUserInfoById(long id);
    UserInfo getUserInfoByUserName(String name);
    int userInfoRegister(String name, String password);
    ResponseEntity login(UserInfo userInfo);

    String logout();
}
