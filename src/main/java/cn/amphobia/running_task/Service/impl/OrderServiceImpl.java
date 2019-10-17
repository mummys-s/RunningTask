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
                 String lastNumber,
                 String goodNumber,
                 String createTime,
                 String username,
                 String userTelephone,
                 String other);

    //查询全部订单---分页
    List<Orders> getOrdersList(int pageNo,int pageSize);

    //查询我发布订单
    List<Orders> getMyOrdersList(String userTelephone);
    //查询我跑腿的订单
    List<Orders> getMyRunOrdersList(String runTelephone);
    //查询订单<!--//配合接单用-->
    Orders getOrders(String orderId);

    //接单
    int getOrder(String order_id,String runName,String runTelephone);
    //完成订单
    int overOrder(String order_id);
    //取消订单
    int removeOrder(String order_id);

    //<!--获取赏金-->
    double getMoney(String order_id);

    //<!--获取用户余额-->
    double getUmoney(String userTelephone);

    double getRumoney(String runTelephone);


    //<!--跑腿addMoney-->
    int addMoney(String runTelephone,double rmoney);

     //<!--用户subMoney-->
    int subMoney(String userTelephone,double umoney);

}
