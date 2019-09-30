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

    //查询全部订单---分页
    List<Orders> getOrdersList(int pageNo,int pageSize);

    //查询个人订单
    List<Orders> getMyOrdersList(String telephone);

    //接单
    int getOrder(String order_id,String runName,String runTelephone);

    //取消订单
    int removeOrder(String order_id);

    //完成订单
    int overOrder(String order_id);
}
