package com.hanchenhao.account.DAO.Implement;

import com.hanchenhao.account.DAO.Mapper.UserInfoMapper;
import com.hanchenhao.account.Model.Persistence.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserInfoDaoImpl implements UserInfoDAO {

    private final UserInfoMapper mapper;

    @Autowired
    public UserInfoDaoImpl(UserInfoMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public UserInfo getUserInfoById(long id) {
        return mapper.getUserInfoById(id);
    }

    @Override
    public void userInfoRegister(UserInfo userInfo) {
         mapper.userInfoRegister(userInfo);
    }

}
