package com.hanchenhao.account.Controller;

import com.hanchenhao.account.Converter.Service.UserInfoServiceConverter;
import com.hanchenhao.account.Exception.InvalidParamException;
import com.hanchenhao.account.Exception.ServiceException;
import com.hanchenhao.account.Model.Service.UserInfo;
import com.hanchenhao.account.Service.UserInfoService;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserInfoService userInfoService;
    private final UserInfoServiceConverter commonDataToService;

    public UserController(UserInfoService userInfoService, UserInfoServiceConverter converter) {
        this.userInfoService = userInfoService;
        this.commonDataToService = converter;
    }

    @GetMapping(value = "/userinfo")
    public ResponseEntity<UserInfo> getUserInfoById(@RequestParam("id") long id) {
        if (!isLegalParam(id)) {
            throw new InvalidParamException(String.format("用户id:%s不合法 ", id));
        }
        return ResponseEntity.ok(commonDataToService.convert(userInfoService.getUserInfoById(id)));
    }

    private boolean isLegalParam(long id) {
        return id > 0;
    }
}
