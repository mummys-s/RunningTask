package cn.amphobia.running_task.Service.impl;

import cn.amphobia.running_task.bean.Address;

import java.util.List;

public interface AddressServiceImpl {

    //添加地址
    int addAddress(String id,String address,String status,String telephone);

    //修改地址
    int updateAddress(String address,String status,String telephone,String id);

    //查找地址
    List<Address> getAddress(String telephone);
}
