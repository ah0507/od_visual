package net.chensee.service.impl;

import net.chensee.dao.UserDao;
import net.chensee.entity.po.UserLoginPo;
import net.chensee.entity.vo.UserVo;
import net.chensee.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserVo findUser(String username, String password) {
        UserLoginPo userLoginPo = userDao.findUser(username, password);
        if (userLoginPo != null) {
            UserVo user = new UserVo();
            BeanUtils.copyProperties(userLoginPo, user);
            return user;
        } else {
            return null;
        }
    }

    @Override
    public UserVo findUserById(String userId) {
        UserLoginPo userLoginPo = userDao.findUserById(userId);
        if (userLoginPo != null) {
            UserVo user = new UserVo();
            BeanUtils.copyProperties(userLoginPo, user);
            return user;
        } else {
            return null;
        }

    }

    @Override
    public List<UserVo> getUsers(int pageNum, int pageSize) {
        List<UserLoginPo> users = userDao.getUsers(pageNum, pageSize);
        List<UserVo> userVos = new ArrayList<>();
        for (UserLoginPo userLoginPo : users) {
            UserVo userVo = new UserVo();
            BeanUtils.copyProperties(userLoginPo, userVo);
            userVos.add(userVo);
        }
        return userVos;
    }

    @Override
    public void addUser(UserLoginPo userLoginPo) {
        userDao.addUser(userLoginPo);
    }

    @Override
    public void delUser(String userId) {
        userDao.delUser(userId);
    }

    @Override
    public long getUsersCount() {
        return userDao.getUsersCount();
    }

}
