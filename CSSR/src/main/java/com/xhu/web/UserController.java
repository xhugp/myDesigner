package com.xhu.web;

import com.xhu.dto.PageBean;
import com.xhu.dto.ResultBean;
import com.xhu.entity.User;
import com.xhu.service.UserService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by gp on 2018/4/4.
 */
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "user/add",method = RequestMethod.POST)
    private ResultBean addUser(@RequestBody User user){
        userService.add(user);
        return new ResultBean("200","添加成功");
    }
    @RequestMapping(value = "user/update",method = RequestMethod.POST)
    private ResultBean updateUser(@RequestBody User user){
        userService.update(user);
        return new ResultBean("200","修改成功");
    }

    @RequestMapping(value = "user/list",method = RequestMethod.POST)
    private ResultBean list(@RequestBody Map<String,Object> reqMap){
        Integer currentPage = (Integer) reqMap.get("currentPage");
        Integer pageSize = (Integer) reqMap.get("pageSize");
        JSONObject json = JSONObject.fromObject(reqMap.get("user"));
        User user = (User) JSONObject.toBean(json,User.class);
        PageBean pageBean = userService.list(currentPage,pageSize,user);
        return new ResultBean("200",pageBean,"用户列表");
    }

    @RequestMapping(value = "user/list-lately",method = RequestMethod.POST)
    private ResultBean list_lately(@RequestBody Map<String,Object> reqMap){
        Integer currentPage = (Integer) reqMap.get("currentPage");
        Integer pageSize = (Integer) reqMap.get("pageSize");
        Integer companyId = Integer.valueOf((String)reqMap.get("companyId")) ;
        PageBean pageBean = userService.latelyUsers(currentPage,pageSize,companyId);
        return new ResultBean("200",pageBean,"用户列表");
    }

    @RequestMapping(value = "user/login",method = RequestMethod.POST)
    private ResultBean login(@RequestBody User user){
        userService.login(user);
        return new ResultBean("200","登录成功");
    }

    @RequestMapping(value = "user/{userId}",method = RequestMethod.DELETE)
    private ResultBean delete(@PathVariable("userId") Integer userId){
        userService.delete(userId);
        return new ResultBean("200","注销成功");
    }

    @RequestMapping(value = "user/{userId}",method = RequestMethod.GET)
    private ResultBean getUser(@PathVariable("userId") Integer userId){
        return new ResultBean("200",userService.getUser(userId),"用户信息");
    }
}
