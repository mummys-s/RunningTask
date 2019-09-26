package cn.amphobia.running_task.Service;

import cn.amphobia.running_task.Service.impl.OrderServiceImpl;
import cn.amphobia.running_task.bean.Orders;
import cn.amphobia.running_task.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService implements OrderServiceImpl {

    @Autowired
    private OrderMapper orderMapper;


    @Override
    public int addOrder(String order_id, String good_name, String good_location, String end_time, String end_address, String money, String telephone, String createTime) {
        return orderMapper.addOrder(order_id, good_name, good_location, end_time, end_address, money, telephone,createTime);
    }

    @Override
    public List<Orders> getOrdersList() {
        return orderMapper.getOrdersList();
    }

    @Override
    public List<Orders> getMyOrdersList(String telephone) {
        return orderMapper.getMyOrdersList(telephone);
    }
}