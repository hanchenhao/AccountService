package com.hanchenhao.account.Model.Persistence;

import lombok.Builder;
import lombok.Data;
import java.time.LocalTime;

@Data
@Builder
public class UserInfo {
    private String id;
    private String userName;
    private String password;
    private LocalTime createdAt;
    private LocalTime updatedAt;
}
