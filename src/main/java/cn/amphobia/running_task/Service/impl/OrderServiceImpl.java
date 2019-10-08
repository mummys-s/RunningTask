package cn.amphobia.running_task.Service.impl;

import cn.amphobia.running_task.bean.Orders;

import java.util.List;

public interface OrderServiceImpl {


    //发布订单
    int addOrder(String order_id,
                 String endAddress,
                 String money,
                 String goodName,
                 String telephone,
                 String createTime,
                 String username,
                 String other);

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
