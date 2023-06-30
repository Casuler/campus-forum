package com.gatsby.controller;

import com.gatsby.entity.RestBean;
import com.gatsby.entity.user.AccountInfo;
import com.gatsby.entity.user.AccountUser;
import com.gatsby.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Resource
    UserService service;
    @GetMapping("/me")
    public RestBean<AccountUser> me(@SessionAttribute("account") AccountUser user){
        return RestBean.success(user);
    }

    @PostMapping("/save-info")
    public RestBean<String> saveInfo(@RequestBody AccountInfo info,
                                   @SessionAttribute("account") AccountUser user){
        info.setUid(user.getId());
        if(service.saveUserInfo(info)){
            return RestBean.success();
        } else {
            return RestBean.failure(400,"昵称已被其他用户使用");
        }
    }
}
