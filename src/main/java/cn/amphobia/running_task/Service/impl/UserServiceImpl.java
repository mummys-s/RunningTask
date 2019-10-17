package cn.amphobia.running_task.Service.impl;

import cn.amphobia.running_task.bean.User;

import java.util.List;

public interface UserServiceImpl{

    //查询所有客户
    List<User> getUserList();

    //根据电话查询单个用户
    User getUser(String telephone);

    //注册，添加用户
    int addUser(String id,String username,String password,String telephone,String create_time);

    //修改密码
    int updatePassword(String password,String telephone);



}
