package cn.amphobia.running_task.Mapper;

import cn.amphobia.running_task.bean.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    //查询所有用户
    List<User> userList();

    //根据电话查询单个用户
    User getUser(@Param("telephone") String telephone);

}
