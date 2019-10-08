package cn.amphobia.running_task.mapper;

import cn.amphobia.running_task.bean.Address;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressMapper {

    //添加地址
    int addAddress(@Param("id") String id,
                   @Param("address") String address,
                   @Param("status") String status,
                   @Param("telephone") String telephone,
                   @Param("username") String username,
                   @Param("userTelephone") String userTelephone);

    //修改地址
    int updateAddress(@Param("address") String address,
                      @Param("username") String username,
                      @Param("status") String status,
                      @Param("telephone") String telephone,
                      @Param("id") String id);

    //查找地址
    List<Address> getAddress(@Param("userTelephone") String userTelephone);
}
