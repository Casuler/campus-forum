package com.gatsby.mapper;

import com.gatsby.entity.auth.Account;
import com.gatsby.entity.user.AccountInfo;
import com.gatsby.entity.user.AccountUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {
    @Select("select * from db_account where username = #{text} or email = #{text}")
    Account findAccountByNameOrEmail(String text);

    @Select("select * from db_account where username = #{text} or email = #{text}")
    AccountUser findAccountUserByNameOrEmail(String text);

    @Select("select * from db_account_info where nickname = #{text}")
    Account findAccountByNickname(String text);

    @Insert("insert into db_account (email, username, password) values (#{email}, #{username}, #{password})")
    int createAccount(String username, String password, String email);

    @Update("update db_account set password = #{password} where email = #{email}")
    int resetPasswordByEmail(String password, String email);

    @Insert("""
            insert into db_account_info (uid, nickname, username, sex, phone, qq, wechat, url, description)
            values (#{uid}, #{username}, #{nickname}, #{sex}, #{phone}, #{qq}, #{wechat}, #{url}, #{description})
            on duplicate key update uid=#{uid}, username=#{username}, nickname=#{nickname}, sex=#{sex}, phone=#{phone}, qq=#{qq},
            wechat=#{wechat}, url=#{url}, description=#{description}
            """)
    void saveInfo(AccountInfo info);

    @Update("update db_account_info set nickname=#{name} where uid=#{uid}")
    void updateNickname(String name, int uid);
}
