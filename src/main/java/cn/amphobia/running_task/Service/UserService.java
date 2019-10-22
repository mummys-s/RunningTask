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

    @Override
    public int addUser(String id, String username, String password, String telephone, String create_time) {
        return userMapper.addUser(id,username,password,telephone,create_time);
    }


    @Override
    public int updatePassword(String password, String telephone) {
        return userMapper.updatePassword(password,telephone);
    }

    @Override
    public int addMoney(String money, String telephone) {
        return userMapper.addMoney(money,telephone);
    }


}
