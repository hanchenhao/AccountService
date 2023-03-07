package com.hanchenhao.account.DAO.Implement;

import com.hanchenhao.account.Model.Persistence.UserInfo;

public interface UserInfoDAO {
    UserInfo getUserInfoById(long id);
    void userInfoRegister(UserInfo userInfo);

}
