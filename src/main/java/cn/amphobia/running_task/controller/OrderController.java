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
        orderMap.put("orderId",orderId);
        orderMap.put("msg", msg);
        orderMap.put("successful", successful);

        return JSONObject.toJSONString(orderMap);
    }

    //查询全部订单 pageNo（页码）、pageSize（每页条数）
    @GetMapping("orderList")
    public String getOrdersList(@RequestParam(value = "pageNo") int pageNo,
                                @RequestParam(value = "pageSize") int pageSize) {
        List<Orders> list = orderServiceImpl.getOrdersList(pageNo,pageSize);
        System.out.println("list------->"+list);
        String name = "";
        String msg = "";
        int status = 0;
        int successful = -1;
        if (list.size() == 0) {
            msg = "我也是有底线的!";
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

    //接单
    @GetMapping("getOrder")
    public String getOrder(@RequestParam(value = "orderId") String orderId,
                           @RequestParam(value = "runName") String runName,
                           @RequestParam(value = "runTelephone") String runTelephone) {

        String msg = "";
        int successful = 0;
        String status = "";
        int row = orderServiceImpl.getOrder(orderId,runName,runTelephone);
        if(row == 1){
            msg = "接单成功!";
            status = "正在接单...";
            successful = 0;
        }else {
            msg = "接单失败";
            successful = 1;
        }

        orderMap.put("msg", msg);
        orderMap.put("successful",successful);
        orderMap.put("status",status);

        return JSONObject.toJSONString(orderMap);
    }

    //取消接单
    @GetMapping("removeOrder")
    public String removeOrder(@RequestParam(value = "orderId") String orderId) {

        String msg = "";
        int successful = 0;
        String status = "";

        int row = orderServiceImpl.removeOrder(orderId);
        if(row == 1){
            msg = "订单取消成功!";
            status = "订单取消";
            successful = 0;
        }else {
            msg = "订单取消失败";
            successful = 1;
        }

        orderMap.put("msg", msg);
        orderMap.put("successful",successful);
        orderMap.put("status",status);
        return JSONObject.toJSONString(orderMap);
    }

    //完成订单

    @GetMapping("overOrder")
    public String voerOrder(@RequestParam(value = "orderId") String orderId) {

        String msg = "";
        int successful = 0;
        String status = "";
        int row = orderServiceImpl.overOrder(orderId);
        if(row == 1){
            msg = "订单完成!";
            status = "订单完成";
            successful = 0;
        }else {
            msg = "订单未完成";
            successful = 1;
        }

        orderMap.put("msg", msg);
        orderMap.put("successful",successful);
        orderMap.put("status",status);
        return JSONObject.toJSONString(orderMap);
    }

}
