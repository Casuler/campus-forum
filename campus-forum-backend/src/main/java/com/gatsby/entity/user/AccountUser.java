package com.gatsby.entity.user;

import lombok.Data;

@Data
public class AccountUser {
    Long id;
    String username;
    String email;
    String avatar;
}
