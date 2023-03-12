package com.hanchenhao.account.Model.Persistence;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {
    private long id;
    private int status;
    private String userName;
    private String password;
    private String salt;    // 加密盐值
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String roles;    //用户所有角色值，用于shiro做角色权限的判断
    private String perms;    //用户所有权限值，用于shiro做资源权限的判断
}
