package cn.amphobia.running_task.controller;

import cn.amphobia.running_task.Service.impl.UserServiceImpl;
import cn.amphobia.running_task.bean.User;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;

import static cn.amphobia.running_task.util.VertifyCode.requestData;

@RestController
@RequestMapping("/user")
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
        String usertelephone = "";
        int status = 0;
        int successful = 0;
        if (user == null) {
            msg = "此用户不存在";
            name = "";
            successful = -1;
        } else if (!user.getPassword().equals(password)) {
            msg = "密码错误";
            successful = 1;
        } else {
            msg = "成功";
            name = user.getUsername();
            status = user.getStatus();
            usertelephone = user.getTelephone();
            successful = 0;
        }

        userMap.put("msg", msg);
        userMap.put("usernmae", name);
        userMap.put("status", status);
        userMap.put("successful", successful);
        userMap.put("usertelephone",usertelephone);
        return JSONObject.toJSONString(userMap);
    }

    //注册接口
    @GetMapping("register")
    public String addUser(@RequestParam(value = "username") String username,
                          @RequestParam(value = "telephone") String telephone,
                          @RequestParam(value = "password") String password,
                          @RequestParam(value = "code") String code) {

        String result = requestData("https://webapi.sms.mob.com/sms/verify",
                "appkey=2c7379af92a04&amp;phone=" + telephone + "&amp;zone=86&amp;&amp;code=" + code);
        JSONObject jsonObject = JSONObject.parseObject(result);
        String status = jsonObject.getString("status");
        String msg = "";
        int successful = 0;
        if (status.trim().contains("200")) {
            System.out.println("---------------->");
            System.out.println("添加数据");
            String id = UUID.randomUUID().toString().replace("-", "").toLowerCase();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String create_time = dateFormat.format(new Date());
            System.out.println(id);
            System.out.println(telephone);
            System.out.println(password);
            System.out.println(create_time);
            User user = userServiceImpl.getUser(telephone);
            if (user == null) {
                int row = userServiceImpl.addUser(id,username, password, telephone, create_time);
                if (row == 1) {
                    msg = "验证通过，添加成功";
                    successful = 0;
                }
            } else {
                msg = "用户已被注册";
                successful = 1;
            }
        } else if (status.trim().contains("456")) {
            msg = "国家代码或手机号码为空";
        } else if (status.trim().contains("457")) {
            msg = "手机号码格式错误";
        } else if (status.trim().contains("466")) {
            msg = "请求校验的验证码为空";
        } else if (status.trim().contains("467")) {
            msg = "请求校验验证码频繁（5分钟内同一个appkey的同一个号码最多只能校验三次）";
        } else if (status.trim().contains("468")) {
            msg = "验证码错误";
            System.out.println("验证错误");
        } else if (status.trim().contains("467")) {
            msg = "没有打开服务端验证开关";
        } else if (status.trim().contains("405")) {
            msg = "AppKey为空";
        } else if (status.trim().contains("406")) {
            msg = "AppKey无效";
        }

        userMap.put("status", status);
        userMap.put("msg", msg);
        userMap.put("successful", successful);

        return JSONObject.toJSONString(userMap);
    }


    //修改密码接口
    @GetMapping("updatePassword")
    public String updatePassword(@RequestParam(value = "telephone") String telephone,
                                 @RequestParam(value = "password") String password,
                                 @RequestParam(value = "code") String code) {


        String result = requestData("https://webapi.sms.mob.com/sms/verify",
                "appkey=2c7379af92a04&amp;phone=" + telephone + "&amp;zone=86&amp;&amp;code=" + code);
        JSONObject jsonObject = JSONObject.parseObject(result);
        String status = jsonObject.getString("status");
        String msg = "";
        int successful = 0;
        if (status.trim().contains("200")) {
            System.out.println("---------------->");
            System.out.println("开始更新");
            User user = userServiceImpl.getUser(telephone);
            if (user != null) {
                int row = userServiceImpl.updatePassword(password, telephone);
                if (row == 1) {
                    msg = "修改密码成功!";
                    successful = 0;
                } else {
                    msg = "修改密码失败";
                    successful = 1;
                }
            } else {
                msg = "该用户不存在!";
                successful = -1;
            }
        } else if (status.trim().contains("456")) {
            msg = "国家代码或手机号码为空";
        } else if (status.trim().contains("457")) {
            msg = "手机号码格式错误";
        } else if (status.trim().contains("466")) {
            msg = "请求校验的验证码为空";
        } else if (status.trim().contains("467")) {
            msg = "请求校验验证码频繁（5分钟内同一个appkey的同一个号码最多只能校验三次）";
        } else if (status.trim().contains("468")) {
            msg = "验证码错误";
            System.out.println("验证错误");
        } else if (status.trim().contains("467")) {
            msg = "没有打开服务端验证开关";
        } else if (status.trim().contains("405")) {
            msg = "AppKey为空";
        } else if (status.trim().contains("406")) {
            msg = "AppKey无效";
        }

        userMap.put("status", status);
        userMap.put("msg", msg);
        userMap.put("successful", successful);

        return JSONObject.toJSONString(userMap);
    }

    //查询全部
    @GetMapping("getUserList")
    public String getUserList(){
        Map<String, Object> userMap = new HashMap<>();
        List<User> userList = userServiceImpl.getUserList();
        userMap.put("data",userList);
        return JSONObject.toJSONString(userMap);
    }

    //返回用户金额
    @GetMapping("getUserMoney")
    public String getUserMoney(@RequestParam(value = "telephone") String telephone){
        Map<String, Object> userMap = new HashMap<>();
        User user = userServiceImpl.getUser(telephone);
        String money = "";
        money = user.getMoney();
        userMap.put("money",money);
        return  JSONObject.toJSONString(userMap);
    }


}
