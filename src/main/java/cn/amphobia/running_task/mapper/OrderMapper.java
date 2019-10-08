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
                 @Param("endAddress") String endAddress,
                 @Param("money") String money,
                 @Param("goodName") String goodName,
                 @Param("telephone") String telephone,
                 @Param("createTime") String createTime,
                 @Param("username") String username,
                 @Param("other") String other);

    //查询全部订单---分页
    List<Orders> getOrdersList(Map<String, Object> data);

    //查询全部订单
    List<Orders> getMyOrdersList(@Param("telephone") String telephone);

    //接单
    int updateOrder(@Param("orderId") String orderId,
                    @Param("runName") String runName,
                    @Param("runTelephone") String runTelephone);

    //取消订单
    int removeOrder(@Param("orderId") String order_id);

    //完成订单
    int overOrder(@Param("orderId") String order_id);

}
