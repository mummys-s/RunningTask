package cn.amphobia.running_task.Service;

import cn.amphobia.running_task.Service.impl.OrderServiceImpl;
import cn.amphobia.running_task.bean.Orders;
import cn.amphobia.running_task.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderService implements OrderServiceImpl {

    @Autowired
    private OrderMapper orderMapper;


    @Override
    public int addOrder(String order_id, String good_name, String good_location, String end_time,
                        String end_address, String money, String telephone, String lastNumber,
                        String goodNumber, String createTime, String username, String userTelephone, String other) {
        return orderMapper.addOrder(order_id, good_name, good_location, end_time,
                end_address, money, telephone,lastNumber,goodNumber,createTime,username,userTelephone,other);
    }

    /*@Override
        public int addOrder(String order_id, String good_name, String good_location, String end_time,String end_address, String money, String telephone,String lastNumber,String goodNumber, String createTime) {
            return orderMapper.addOrder(order_id, good_name, good_location, end_time, end_address, money, telephone,lastNumber,goodNumber,createTime);
        }*/
    //查询全部订单---分页
    @Override
    public List<Orders> getOrdersList(int pageNo,int pageSize) {

        Map<String, Object> data = new HashMap<>();
        data.put("currIndex", pageNo*pageSize);
        data.put("pageSize", pageSize);
        return orderMapper.getOrdersList(data);
    }
    //查询我发布订单
    @Override
    public List<Orders> getMyOrdersList(String userTelephone) {
        return orderMapper.getMyOrdersList(userTelephone);
    }
    //查询我跑腿的订单
    @Override
    public List<Orders> getMyRunOrdersList(String runTelephone) {
        return orderMapper.getMyRunOrdersList(runTelephone);
    }

    //查询订单 <!--//配合接单用-->
    @Override
    public Orders getOrders(String order_id) {
        return orderMapper.getOrders(order_id);
    }



    //接单
    @Override
    public int getOrder(String order_id,String runName,String runTelephone) {

//        Map<String, String> data1 = new HashMap<>();
//        data1.put("currIndex",order_id);
//        data1.put("pageSize",runName);
//        data1.put("pageSize",runTelephone);
        return orderMapper.updateOrder(order_id,runName,runTelephone);
    }
    //完成订单
    @Override
    public int overOrder(String order_id) {
        return orderMapper.overOrder(order_id);
    }
    //取消订单
    @Override
    public int removeOrder(String order_id) {
        return orderMapper.removeOrder(order_id);
    }
    //<!--获取赏金-->
    @Override
    public double getMoney(String order_id) {
        return orderMapper.getMoney(order_id);
    }

    @Override
    public double getUmoney(String userTelephone) {
        return orderMapper.getUmoney(userTelephone);
    }

    @Override
    public double getRumoney(String runTelephone) {
        return orderMapper.getRumoney(runTelephone);
    }

    //<!--跑腿addMoney-->
    @Override
    public int addMoney(String runTelephone,double rmoney) {
        return orderMapper.addMoney(runTelephone,rmoney);
    }
    //<!--用户subMoney-->
    @Override
    public int subMoney(String userTelephone,double umoney) {
        return orderMapper.subMoney(userTelephone,umoney);
    }


}
