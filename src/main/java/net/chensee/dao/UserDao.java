package net.chensee.dao;

import net.chensee.entity.po.UserLoginPo;

import java.util.List;

public interface UserDao {
    UserLoginPo findUser(String username, String password);

    UserLoginPo findUserById(String userId);

    List<UserLoginPo> getUsers(int pageNum, int pageSize);

    void addUser(UserLoginPo userLoginPo);

    void delUser(String userId);

    long getUsersCount();
}
