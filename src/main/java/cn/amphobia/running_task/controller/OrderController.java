package cn.amphobia.running_task.controller;


import cn.amphobia.running_task.Service.impl.OrderServiceImpl;
import cn.amphobia.running_task.bean.Orders;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/orders")
public class OrderController {
    Map<String, Object> orderMap = new HashMap<>();

    @Autowired
    private OrderServiceImpl orderServiceImpl;

    //提交订单
    @GetMapping("addOrder")
    public String getOrders(
            @RequestParam(value = "goodName") String goodName,
            @RequestParam(value = "goodLocation") String goodLocation,
            @RequestParam(value = "endTime") String endTime,
            @RequestParam(value = "endAddress") String endAddress,
            @RequestParam(value = "money") String money,
            @RequestParam(value = "telephone") String telephone) {
//        System.out.println("添加数据");
        String orderId = UUID.randomUUID().toString().replace("-", "").toLowerCase();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String create_time = dateFormat.format(new Date());
        String msg = "";
        int successful = 0;
//        System.out.println("id值======>"+orderId);
        int row = orderServiceImpl.addOrder(orderId, goodName, goodLocation, endTime, endAddress, money, telephone,create_time);
        if (row == 1) {
            msg = "发布成功";
            successful = 0;
        } else {
            msg = "发布失败";
            successful = -1;
        }

        orderMap.put("msg", msg);
        orderMap.put("successful", successful);

        return JSONObject.toJSONString(orderMap);
    }

    //查询全部订单
    @GetMapping("orderList")
    public String getOrdersList() {
        List<Orders> list = orderServiceImpl.getOrdersList();
        String name = "";
        String msg = "";
        int status = 0;
        int successful = -1;
        if (list == null) {
            msg = "失败";
        } else {
            msg = "成功";
            successful = 0;
        }

        orderMap.put("msg",msg);
        orderMap.put("successful",successful);
        orderMap.put("data",list);

        return JSONObject.toJSONString(orderMap);
    }

    //查询我的订单
    @GetMapping("getMyOrders")
    public String getMyOrdersList(@RequestParam(value = "telephone") String telephone) {
        List<Orders> myList = orderServiceImpl.getMyOrdersList(telephone);

        String msg = "";
        int status = 0;
        int successful = -1;
        if (myList == null) {
            msg = "失败";

        } else {
            msg = "成功";
            successful = 0;
        }


        orderMap.put("successful",successful);
        orderMap.put("msg",msg);
        orderMap.put("data",myList);

        return JSONObject.toJSONString(orderMap);
    }

}
