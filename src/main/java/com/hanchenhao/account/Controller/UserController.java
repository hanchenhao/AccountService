package com.hanchenhao.account.Controller;

import com.hanchenhao.account.Converter.Service.UserInfoServiceConverter;
import com.hanchenhao.account.Model.Service.UserInfo;
import com.hanchenhao.account.Service.UserInfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserInfoService userInfoService;
    private final UserInfoServiceConverter commonDataToService;
    public UserController(UserInfoService userInfoService, UserInfoServiceConverter converter) {
        this.userInfoService = userInfoService;
        this.commonDataToService = converter;
    }

    @GetMapping("/{id}")
    public UserInfo getUserInfoById(@PathVariable("id") String id){
        System.out.println("id = " + id);
        return commonDataToService.convert(userInfoService.getUserInfoById(id));
    }
}
