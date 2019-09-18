package cn.amphobia.running_task.mapper;

import cn.amphobia.running_task.bean.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    //查询所有用户
    List<User> getUserList();

    //根据电话查询单个用户
    User getUser(@Param("telephone") String telephone);

    //注册添加用户
    int addUser(@Param("id") String id,
                @Param("password") String password,
                @Param("telephone") String telephone,
                @Param("create_time") String create_time);

}
