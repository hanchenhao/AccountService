package com.hanchenhao.account.Converter.Common;

import com.google.common.base.Converter;
import com.hanchenhao.account.Model.Persistence.UserInfo;
import org.springframework.stereotype.Component;

@Component
public class UserInfoCommonConverter extends Converter<UserInfo, com.hanchenhao.account.Model.Common.UserInfo> {
    @Override
    protected com.hanchenhao.account.Model.Common.UserInfo doForward(UserInfo userInfo) {
        return com.hanchenhao.account.Model.Common.UserInfo.builder()
                .userName(userInfo.getUserName())
                .password(userInfo.getPassword())
                .id(userInfo.getId())
                .build();
    }

    @Override
    protected UserInfo doBackward(com.hanchenhao.account.Model.Common.UserInfo userInfo) {
        return UserInfo.builder()
                .userName(userInfo.getUserName())
                .password(userInfo.getPassword())
                .id(userInfo.getId())
                .build();
    }
}
