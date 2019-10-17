package cn.amphobia.running_task.mapper;

import cn.amphobia.running_task.bean.Orders;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface OrderMapper {
    //发布订单
    int addOrder(@Param("orderId") String order_id,
                 @Param("goodName") String good_name,
                 @Param("goodLocation") String good_location,
                 @Param("endTime") String end_time,
                 @Param("endAddress") String end_address,
                 @Param("money") String money,
                 @Param("telephone") String telephone,
                 @Param("lastNumber") String lastNumber,
                 @Param("goodNumber") String goodNumber,
                 @Param("createTime") String createTime,
                 @Param("username") String username,
                 @Param("userTelephone") String userTelephone,
                 @Param("other") String other);

    //查询全部订单---分页 username,userTelephone,other
    List<Orders> getOrdersList(Map<String, Object> data);


    ////查询我发布订单
    List<Orders> getMyOrdersList(@Param("userTelephone") String userTelephone);
    //查询我跑腿发布订单
    List<Orders> getMyRunOrdersList(@Param("runTelephone")String runTelephone);

    //接单
    int updateOrder(@Param("orderId") String orderId,
                    @Param("runName") String runName,
                    @Param("runTelephone") String runTelephone);
    //完成订单
    int overOrder(@Param("orderId") String order_id);
    //取消订单
    int removeOrder(@Param("orderId") String order_id);

    //查询订单<!--//配合接单用-->
    Orders getOrders(@Param("orderId")String order_id);
    //<!--获取赏金-->
    double getMoney(@Param("orderId")String order_id);
    //<!--获取用户余额-->
    double getUmoney(@Param("userTelephone")String userTelephone);

    double getRumoney(@Param("runTelephone")String runTelephone);

    //<!--跑腿addMoney-->
    int addMoney(@Param("runTelephone")String runTelephone,
                 @Param("rmoney")double rmoney);
    //<!--用户subMoney-->
    int subMoney(@Param("userTelephone")String userTelephone,
                 @Param("umoney")double umoney);


}
