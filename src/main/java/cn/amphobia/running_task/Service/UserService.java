package cn.amphobia.running_task.Service;

import cn.amphobia.running_task.Service.impl.UserServiceImpl;
import cn.amphobia.running_task.bean.User;
import cn.amphobia.running_task.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserServiceImpl {

    @Autowired
    private UserMapper userMapper;


    @Override
    public List<User> getUserList() {
        return userMapper.getUserList();
    }

    @Override
    public User getUser(String telephone) {
        return userMapper.getUser(telephone);
    }


}
