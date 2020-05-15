package net.chensee.controller;

import net.chensee.entity.po.ObjectResponse;
import net.chensee.entity.po.UserLoginPo;
import net.chensee.entity.vo.UserVo;
import net.chensee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping(value = "/")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public ObjectResponse login(@RequestBody UserLoginPo userLoginPo, HttpSession session) {
        String username = userLoginPo.getUsername();
        String password = userLoginPo.getPassword();
        if ("".equals(username) || "".equals(password)) {
            return ObjectResponse.fail("用户名或密码不能为空");
        }
        UserVo user = userService.findUser(username, password);
        if (user == null) {
            return ObjectResponse.fail("用户名或密码错误");

        }
        session.setAttribute("user",user);
        return ObjectResponse.ok(user);
    }

    @RequestMapping(value = "getUser", method = RequestMethod.GET)
    @ResponseBody
    public UserVo getUser(HttpSession session) {
        Object obj = session.getAttribute("user");
        UserVo userVo = null;
        if (obj != null) {
            userVo = (UserVo) obj;
        }
        return userVo;
    }

}
