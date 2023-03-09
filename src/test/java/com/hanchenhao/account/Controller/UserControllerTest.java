package com.hanchenhao.account.Controller;

import com.hanchenhao.account.Converter.Service.UserInfoServiceConverter;
import com.hanchenhao.account.Exception.BaseExceptionHandler;
import com.hanchenhao.account.Model.Common.UserInfo;
import com.hanchenhao.account.Service.User.UserInfoService;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.reset;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserInfoService userInfoService;
    @Mock
    private UserInfoServiceConverter commonDataToService;

    @InjectMocks
    private UserController userController;


    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController)
                .setControllerAdvice(new BaseExceptionHandler())
                .build();

    }

    @AfterEach
    public void teardown() {
        reset(userInfoService);
        reset(commonDataToService);
    }


    @Test
    void testGetUserInfoById() throws Exception {
        String name = "hanchenhao";
        long userID = 1;
        String password = "123456";
        var commonUserInfo = UserInfo.builder()
                .userName(name)
                .id(userID)
                .password(password)
                .build();

        Mockito.doReturn(commonUserInfo).when(userInfoService).getUserInfoById(userID);

        var serviceUserInfo = com.hanchenhao.account.Model.Service.UserInfo.builder()
                .userName(name)
                .id(userID)
                .build();
        Mockito.doReturn(serviceUserInfo).when(commonDataToService).convert(commonUserInfo);

        mockMvc.perform(get("/userinfo").param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(content().string(new ObjectMapper().writeValueAsString(serviceUserInfo)));

        Mockito.verify(userInfoService).getUserInfoById(userID);
        Mockito.verify(commonDataToService).convert(commonUserInfo);

    }

    @Test
    void testGetUserInfoByInvalidUserId() throws Exception {
        mockMvc.perform(get("/userinfo").param("id", "-1"))
                .andExpect(status().isBadRequest());
        Mockito.verify(userInfoService, never()).getUserInfoById(-1);

    }
}