package cn.amphobia.running_task.mapper;

import cn.amphobia.running_task.bean.Orders;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

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
                 @Param("createTime") String createTime);

    //查询个人发布订单
    List<Orders> getOrdersList();

    //查询全部订单
    List<Orders> getMyOrdersList(@Param("telephone") String telephone);

}
