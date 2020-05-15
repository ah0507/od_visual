package net.chensee.controller;

import lombok.extern.slf4j.Slf4j;
import net.chensee.common.DateUtil;
import net.chensee.entity.po.*;
import net.chensee.entity.vo.UserVo;
import net.chensee.service.UserService;
import net.chensee.service.VisualService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("visual")
@Slf4j
public class VisualController {

    @Autowired
    private VisualService visualService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "",method = RequestMethod.POST)
    @ResponseBody
    public Map addConfig(@RequestBody ConfigProperties configProperties){
        Map resultMap = new HashMap<>();
        if (configProperties.getPageNumber() == null || configProperties.getPageSize() == null ||
                configProperties.getAddInAndOutTimeValue() == null || configProperties.getAddTimeValue() == null ||
                configProperties.getRegularTravelChanceValue() == null || configProperties.getDistanceValue() == null ||
                configProperties.getEndStationTimeValue() == null || configProperties.getExpireTimeRangeValue() == null ||
                configProperties.getInTimeValue() == null || configProperties.getOutTimeValue() == null ||
                configProperties.getRegularTravelTimeValue() == null || configProperties.getAddTicketValue() == null ||
                configProperties.getCalculateHandleTime() == null || configProperties.getRatedNumber() == null) {
            resultMap.put("code", 0);
            resultMap.put("msg", "请填写完整！");
            return resultMap;
        }
        try {
            configProperties.setCreateTime(DateUtil.getNYRSFMDate(new Date()));
            visualService.addConfig(configProperties);
            resultMap.put("code", 1);
            resultMap.put("msg", "保存成功！");
        } catch (Exception e) {
            resultMap.put("code", 0);
            resultMap.put("msg", "保存失败！");
            e.printStackTrace();
        }
        return resultMap;
    }

    @RequestMapping(value = "/getRoots",method = RequestMethod.GET)
    @ResponseBody
    public ObjectResponse getRoots(@RequestParam(value = "pageNum", required = true)int pageNum,
                                   @RequestParam(value = "pageSize", required = true)int pageSize){
        List<BaseProcess> baseProcesses = visualService.getRoots(pageNum, pageSize);
        long count = visualService.getRootsCount();
        Map map = new HashMap();
        map.put("rows", baseProcesses);
        map.put("total", count);
        return ObjectResponse.ok(map);
    }

    @RequestMapping(value = "/getRootDetail",method = RequestMethod.GET)
    @ResponseBody
    public List<BaseProcess> getChildProcesses(@RequestParam(value = "parentName") String parentName,
                                               @RequestParam(value = "dataTime") String dataTime){
        return visualService.getChildProcesses(parentName,dataTime);
    }

    @RequestMapping(value = "/getLogs",method = RequestMethod.GET)
    @ResponseBody
    public ObjectResponse getLogs(@RequestParam(value = "pageNum", required = true)int pageNum,
                                   @RequestParam(value = "pageSize", required = true)int pageSize){
        List<LoggingPo> logs = visualService.getLogs(pageNum, pageSize);
        long count = visualService.getLogsCount();
        Map map = new HashMap();
        map.put("rows", logs);
        map.put("total", count);
        return ObjectResponse.ok(map);
    }

    @RequestMapping(value = "/getUsers", method = RequestMethod.GET)
    @ResponseBody
    public ObjectResponse getUsers(@RequestParam(value = "pageNum", required = true)int pageNum,
                               @RequestParam(value = "pageSize", required = true)int pageSize) {
        List<UserVo> userVos = userService.getUsers(pageNum,pageSize);
        long count = userService.getUsersCount();
        Map map = new HashMap();
        map.put("rows", userVos);
        map.put("total", count);
        return ObjectResponse.ok(map);
    }

    @RequestMapping(value = "addUser",method = RequestMethod.POST)
    @ResponseBody
    public ObjectResponse addUser(@RequestBody UserLoginPo userLoginPo){
        if (userLoginPo.getUsername().isEmpty() || userLoginPo.getPassword().isEmpty() || userLoginPo.getPower() == null) {
            return ObjectResponse.fail("请填写完整！");
        }
        userService.addUser(userLoginPo);
        return ObjectResponse.ok("添加成功！");
    }

    @RequestMapping(value = "/delUser/{userId}", method = RequestMethod.DELETE)
    @ResponseBody
    public ObjectResponse delUser(@PathVariable(value = "userId") String userId, HttpSession session) {
        userService.delUser(userId);
        UserVo user = (UserVo) session.getAttribute("user");
        if (user != null && userId.equals(user.getId())) {
            session.removeAttribute("user");
        }
        return ObjectResponse.ok("删除成功!");
    }

}
