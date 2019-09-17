package cn.amphobia.running_task.controller;

import cn.amphobia.running_task.Service.impl.UserServiceImpl;
import cn.amphobia.running_task.bean.User;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    Map<String, Object> userMap = new HashMap<>();

    @Autowired
    private UserServiceImpl userServiceImpl;


    //登录接口------根据电话查找用户
    @GetMapping("login")
    public String getUser(@RequestParam(value = "telephone") String telephone,
                          @RequestParam(value = "password") String password) {
        User user = userServiceImpl.getUser(telephone);
        String name = "";
        String msg = "";
        if (user == null) {
            msg = "此用户不存在";
            name = "";
        } else if (!user.getPassword().equals(password)) {
            msg = "密码错误";
        } else {
            msg = "成功";
            name = user.getUsername();
        }

        userMap.put("msg", msg);
        userMap.put("usernmae", name);
        return JSONObject.toJSONString(userMap);
    }

    //注册接口



}
