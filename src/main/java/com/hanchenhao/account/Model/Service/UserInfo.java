package com.hanchenhao.account.Model.Service;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserInfo {
    private long id;
    private String userName;
}
