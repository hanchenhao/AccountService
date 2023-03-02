package com.hanchenhao.account.Model.Common;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserInfo {
    private String id;
    private String userName;
    private String password;
}
