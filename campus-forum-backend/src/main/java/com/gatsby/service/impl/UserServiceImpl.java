package com.gatsby.service.impl;

import com.gatsby.entity.auth.Account;
import com.gatsby.entity.user.AccountInfo;
import com.gatsby.mapper.UserMapper;
import com.gatsby.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserMapper mapper;
    @Override
    public boolean saveUserInfo(AccountInfo info) {
        Account account = mapper.findAccountByNickname(info.getNickname());
        if(account == null) {
            mapper.updateNickname(info.getNickname(), info.getUid());
        } else if(account.getId() != info.getUid()){
            return false;
        }
        mapper.saveInfo(info);
        return true;
    }
}
