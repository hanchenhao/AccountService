package com.hanchenhao.account.Converter.Service;

import com.google.common.base.Converter;
import com.hanchenhao.account.Model.Common.UserInfo;
import org.springframework.stereotype.Component;

@Component
public class UserInfoServiceConverter extends Converter<UserInfo, com.hanchenhao.account.Model.Service.UserInfo> {
    @Override
    protected com.hanchenhao.account.Model.Service.UserInfo doForward(UserInfo userInfo) {
        return com.hanchenhao.account.Model.Service.UserInfo.builder()
                .userName(userInfo.getUserName())
                .id(userInfo.getId())
                .build();
    }

    @Override
    protected UserInfo doBackward(com.hanchenhao.account.Model.Service.UserInfo userInfo) {
        return com.hanchenhao.account.Model.Common.UserInfo.builder()
                .userName(userInfo.getUserName())
                .id(userInfo.getId())
                .build();
    }
}
