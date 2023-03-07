package com.hanchenhao.account.Model.Persistence;

import lombok.Builder;
import lombok.Data;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
public class UserInfo {
    private long id;
    private String userName;
    private String password;
    private String salt;    // 加密盐值
    private LocalTime createdAt;
    private LocalTime updatedAt;
    private Set<String> roles = new HashSet<>();    //用户所有角色值，用于shiro做角色权限的判断
    private Set<String> perms = new HashSet<>();    //用户所有权限值，用于shiro做资源权限的判断
}
