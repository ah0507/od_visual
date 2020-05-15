package net.chensee.service;

import net.chensee.entity.po.ObjectResponse;
import net.chensee.entity.po.UserLoginPo;
import net.chensee.entity.vo.UserVo;

import java.util.List;

public interface UserService {

    UserVo findUser(String username, String password);

    UserVo findUserById(String userId);

    List<UserVo> getUsers(int pageNum, int pageSize);

    void addUser(UserLoginPo userLoginPo);

    void delUser(String userId);

    long getUsersCount();

}
