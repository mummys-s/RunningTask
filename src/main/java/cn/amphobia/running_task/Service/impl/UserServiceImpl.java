package cn.amphobia.running_task.Service.impl;

import cn.amphobia.running_task.bean.User;

import java.util.List;

public interface UserServiceImpl{

    //查询所有客户
    List<User> getUserList();

    //根据电话查询单个用户
    User getUser(String telephone);



}
