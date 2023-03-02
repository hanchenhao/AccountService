package com.hanchenhao.account.Controller;

import com.hanchenhao.account.Model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class UserController {
    private final AtomicLong counter = new AtomicLong();
    @GetMapping("/v1/greeting")
    public  User sayHello(@RequestParam("name") String name){
        return new User(name,counter.incrementAndGet() + "");
    }
}
