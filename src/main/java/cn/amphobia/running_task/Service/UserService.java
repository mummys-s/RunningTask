package cn.amphobia.running_task.Service;

import cn.amphobia.running_task.Mapper.UserMapper;
import cn.amphobia.running_task.Service.impl.UserServiceImpl;
import cn.amphobia.running_task.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserServiceImpl {

    @Autowired
    UserMapper userMapper;

    public List<User> userList(){
        return userMapper.userList();
    }

    @Override
    public User getUser(String telephone) {
        return userMapper.getUser(telephone);
    }


}
