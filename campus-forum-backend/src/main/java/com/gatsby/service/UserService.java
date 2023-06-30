package com.gatsby.service;

import com.gatsby.entity.user.AccountInfo;

public interface UserService {
    boolean saveUserInfo(AccountInfo info);
}
