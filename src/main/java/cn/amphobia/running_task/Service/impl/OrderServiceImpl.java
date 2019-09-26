package cn.amphobia.running_task.Service.impl;

import cn.amphobia.running_task.bean.Orders;

import java.util.List;

public interface OrderServiceImpl {
    //发布订单
    int addOrder(String order_id,
                 String good_name,
                 String good_location,
                 String end_time,
                 String end_address,
                 String money,
                 String telephone,
                 String createTime);

    //查询个人发布订单
    List<Orders> getOrdersList();

    //查询全部订单
    List<Orders> getMyOrdersList(String telephone);
}
