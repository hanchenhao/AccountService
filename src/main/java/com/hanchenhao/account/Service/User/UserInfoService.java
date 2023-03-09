package com.hanchenhao.account.Service.User;

import com.hanchenhao.account.Model.Common.UserInfo;


public interface UserInfoService {
    UserInfo getUserInfoById(long id);
    UserInfo getUserInfoByUserName(String name);
    int userInfoRegister(String name, String password);
    String login(UserInfo userInfo);

}
