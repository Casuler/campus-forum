package com.gatsby.entity.user;

import lombok.Data;

@Data
public class AccountInfo {
    int uid;
    String username;
    String nickname;
    String sex;
    String phone;
    String qq;
    String wechat;
    String url;
    String description;
}
