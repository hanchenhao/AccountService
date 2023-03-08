package com.hanchenhao.account.Controller;

import com.hanchenhao.account.Converter.Service.UserInfoServiceConverter;
import com.hanchenhao.account.Exception.InvalidParamException;
import com.hanchenhao.account.Model.Service.UserInfo;
import com.hanchenhao.account.Service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping("/user")
public class UserController {
    private final UserInfoService userInfoService;
    private final UserInfoServiceConverter commonDataToService;

    @Autowired
    public UserController(UserInfoService userInfoService, UserInfoServiceConverter converter) {
        this.userInfoService = userInfoService;
        this.commonDataToService = converter;
    }

    @GetMapping("/userinfo")
    public ResponseEntity<UserInfo> getUserInfoById(@RequestParam("id") long id) {

        if (!isLegalParam(id)) {
            throw new InvalidParamException(String.format("用户id:%s不合法 ", id));
        }
        var userinfo = userInfoService.getUserInfoById(id);
        var serviceData = commonDataToService.convert(userinfo);
        return ResponseEntity.ok(serviceData);
    }

    @GetMapping("/test/test1")
    public String test1() {
        return "hhh";
    }

    @GetMapping("/")
    public String home() {
        return "home hello";
    }

    @GetMapping("/test/test")
    public String test() {
        return "test";
    }

    @GetMapping("/login")
    public String longin() {
        return "let's login";
    }


    @PostMapping("/register")
    public ResponseEntity<UserInfo> userRegister(@RequestParam("username") String name,
                                                 @RequestParam("password") String password) {
//        userInfoService.userInfoRegister(name, password);
        UserInfo user = UserInfo.builder()
                .userName(name)
                .build();
        return ResponseEntity.ok(user);
    }

    private boolean isLegalParam(long id) {
        return id > 0;
    }
}
