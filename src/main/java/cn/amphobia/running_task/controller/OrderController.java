package cn.amphobia.running_task.controller;


import cn.amphobia.running_task.Service.impl.OrderServiceImpl;
import cn.amphobia.running_task.bean.Orders;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/orders")
public class OrderController {
    /*Map<String, Object> orderMap = new HashMap<>();*/

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
            @RequestParam(value = "lastNumber") String lastNumber,
            @RequestParam(value = "goodNumber") String goodNumber,
            @RequestParam(value = "telephone") String telephone,
            @RequestParam(value = "username") String username,
            @RequestParam(value = "userTelephone") String userTelephone,
            @RequestParam(value = "other") String other
            ) {
        Map<String, Object> orderMap1 = new HashMap<>();
        double umoney = 0;
        //获取用户余额
        umoney = (double) orderServiceImpl.getUmoney(userTelephone);

        DecimalFormat df = new DecimalFormat( "0.00");
        // umoney = Double.parseDouble(df.format(umoney)) ;
        double money1 = Double.parseDouble(money);

        double money2 = 0;
        //结算
        money2 = umoney-money1;
        money2 = Double.parseDouble(df.format(money2)) ;
        int successful = 0;
        String msg = "";
        if(money2 < 0 || money1<0 || money1==0){
            msg="余额不足请充值";
            successful = 1;
        }else {
            //结算
            orderServiceImpl.subMoney(userTelephone,money2);

//        System.out.println("添加数据"); other
            String orderId = UUID.randomUUID().toString().replace("-", "").toLowerCase();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String create_time = dateFormat.format(new Date());


//        System.out.println("id值======>"+orderId);
            int row = orderServiceImpl.addOrder(orderId, goodName, goodLocation, endTime, endAddress, money, telephone, lastNumber, goodNumber, create_time, username, userTelephone, other);
            if (row == 1) {
                msg = "发布成功";
                successful = 0;
            } else {
                msg = "发布失败";
                successful = -1;
            }

        }

        orderMap1.put("msg", msg);
        orderMap1.put("successful", successful);
//        orderMap1.put("umoney",umoney);

        return JSONObject.toJSONString(orderMap1);
    }

    //查询全部订单 pageNo（页码）、pageSize（每页条数）
    @GetMapping("orderList")
    public String getOrdersList(@RequestParam(value = "pageNo") int pageNo,
                                @RequestParam(value = "pageSize") int pageSize) {
        Map<String, Object> orderMap = new HashMap<>();
        List<Orders> list = orderServiceImpl.getOrdersList(pageNo,pageSize);
        String name = "";
        String msg = "";
        int status = 0;
        int successful = 0;
        if (list == null) {
            msg = "失败";
            successful = 0;
        } else  if (list.size()==0){
            successful = -1;
            msg = "没有更多数据";
        }else {
            msg = "成功";
            successful = 1;
        }

        orderMap.put("msg",msg);
        orderMap.put("successful",successful);
        orderMap.put("data",list);


        return JSONObject.toJSONString(orderMap);
    }

    //查询我发布订单
    @GetMapping("getMyOrdersList")
    public String getMyOrdersList(@RequestParam(value = "userTelephone") String userTelephone) {
        Map<String, Object> orderMap = new HashMap<>();
        List<Orders> myList = orderServiceImpl.getMyOrdersList(userTelephone);

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
    //查询我跑腿的订单
    @GetMapping("getMyRunOrdersList")
    public String getMyRunOrdersList(@RequestParam(value = "runTelephone") String runTelephone) {
        Map<String, Object> orderMap = new HashMap<>();
        List<Orders> myList = orderServiceImpl.getMyRunOrdersList(runTelephone);

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
        Map<String, Object> orderMap = new HashMap<>();

        Orders order = orderServiceImpl.getOrders(orderId);//<!--//配合接单用-->

        String msg = "";
        int successful = 0;

        if(order.getStates().trim().equals("未接单")){

            int row = orderServiceImpl.getOrder(orderId,runName,runTelephone);
            if(row == 1){
                msg = "接单成功!";
                successful = 0;
            }else {
                msg = "接单失败";
                successful = 1;
            }

        }else if(order.getStates().trim().equals("订单取消")){
            msg=order.getStates().trim();

        }else if(order.getStates().trim().equals("正在取件")){
            msg=order.getStates().trim();
        }






        orderMap.put("msg", msg);
        orderMap.put("successful",successful);
        orderMap.put("nihao0000000","*********---------->>>>>");

        return JSONObject.toJSONString(orderMap);
    }

    //完成订单

    @GetMapping("overOrder")
    public String voerOrder(@RequestParam(value = "orderId") String orderId,
                            @RequestParam(value = "runTelephone") String runTelephone) {
        Map<String, Object> orderMap = new HashMap<>();

        String msg = "";
        int successful = 0;
        double umoney = 0, money= 0, rmoney= 0;

         money = (double) orderServiceImpl.getMoney(orderId);
        // umoney = (double) orderServiceImpl.getUmoney(userTelephone);
         rmoney = (double) orderServiceImpl.getRumoney(runTelephone);

        DecimalFormat df = new DecimalFormat( "0.00");

      //  System.out.println("money:"+money);
      //  umoney =  (umoney - money);

      //  System.out.println("umoney"+umoney);
        rmoney =  (rmoney + (money * 0.8));

       // System.out.println("rmoney:"+rmoney);

//        df.format(d1)

        rmoney = Double.parseDouble(df.format(rmoney)) ;
       // umoney = Double.parseDouble(df.format(umoney)) ;

      //  System.out.println("df.rmoney:"+rmoney);
       // System.out.println("df.umoney"+umoney);


        int row = orderServiceImpl.overOrder(orderId);
        System.out.println("row:"+row);
        if(row == 1){
            msg = "订单完成!";
            successful = 0;
             orderServiceImpl.addMoney(runTelephone,rmoney);
             //orderServiceImpl.subMoney(userTelephone,umoney);
        }else {
            msg = "订单未完成";
            successful = 1;
        }

        orderMap.put("msg", msg);
        orderMap.put("successful",successful);

        return JSONObject.toJSONString(orderMap);
    }

    //取消单
    @GetMapping("removeOrder")

    public String removeOrder(@RequestParam(value = "orderId") String orderId,
                              @RequestParam(value = "userTelephone") String userTelephone,
                              @RequestParam(value = "money") String money) {
        Map<String, Object> orderMap = new HashMap<>();


        String msg = "";
        int successful = 0;
        DecimalFormat df = new DecimalFormat( "0.00");
        // umoney = Double.parseDouble(df.format(umoney)) ;
        double umoney = 0;

        double money11 = Double.parseDouble(money);
        //获取用户余额
        umoney = (double) orderServiceImpl.getUmoney(userTelephone);

        //结算
        double moneyNew = 0 ;



        moneyNew = umoney + money11;
        moneyNew = Double.parseDouble(df.format(moneyNew)) ;
        int row = orderServiceImpl.removeOrder(orderId);
        if(row == 1){
            msg = "订单取消成功!";
            successful = 0;
            orderServiceImpl.subMoney(userTelephone,moneyNew);
        }else {
            msg = "订单取消失败";
            successful = 1;
        }

        orderMap.put("msg", msg);
        orderMap.put("successful",successful);

        return JSONObject.toJSONString(orderMap);
    }

    @GetMapping("getOrders")
    public String getOrders(@RequestParam(value = "orderId") String orderId) {
        Map<String, Object> orderMap = new HashMap<>();

        String msg = "";
        int successful = 0;

//        int row = orderServiceImpl.getOrder(orderId);
        Orders order = orderServiceImpl.getOrders(orderId);
        if(order.getStates().trim().equals("未接单")){
            msg=order.getStates();
        }else {
            msg=order.getStates();
        }

        orderMap.put("msg", msg);
//        orderMap.put("order",order);

        return JSONObject.toJSONString(orderMap);
    }
    //<!--获取赏金-->

    @GetMapping("getMoney")
    public String getMoney(@RequestParam(value = "orderId") String orderId) {
        Map<String, Object> orderMap = new HashMap<>();

        String msg = "";
        int successful = 0;

//        int row = orderServiceImpl.getOrder(orderId);
        double money = orderServiceImpl.getMoney(orderId);


        orderMap.put("msg", msg);
        orderMap.put("money",money);

        return JSONObject.toJSONString(orderMap);
    }

    //<!--获取用户余额-->

    @GetMapping("getUmoney")
    public String getUmoney(@RequestParam(value = "userTelephone") String userTelephone) {
        Map<String, Object> orderMap = new HashMap<>();

        String msg = "";
        int successful = 0;

//        int row = orderServiceImpl.getOrder(orderId);
        double money = orderServiceImpl.getUmoney(userTelephone);


        orderMap.put("msg", msg);
        orderMap.put("user ---- money :",money);

        return JSONObject.toJSONString(orderMap);
    }

    //<!--跑腿addMoney-->

    @GetMapping("addMoney")
    public String addMoney(@RequestParam(value = "runTelephone") String runTelephone,
                           @RequestParam(value = "rmoney") double rmoney) {
        Map<String, Object> orderMap = new HashMap<>();

        String msg = "addMoney";
        int successful = 0;

//        int row = orderServiceImpl.getOrder(orderId);
        int row = orderServiceImpl.addMoney(runTelephone,rmoney);


        orderMap.put("msg", msg);
        orderMap.put("user ---- row :",row);

        return JSONObject.toJSONString(orderMap);
    }

    //<!--用户subMoney-->

    @GetMapping("subMoney")
    public String subMoney(@RequestParam(value = "userTelephone") String userTelephone,
                           @RequestParam(value = "umoney") double umoney) {
        Map<String, Object> orderMap = new HashMap<>();

        String msg = "subMoney";
        int successful = 0;

//        int row = orderServiceImpl.getOrder(orderId);
        int row = orderServiceImpl.subMoney(userTelephone,umoney);


        orderMap.put("msg", msg);
        orderMap.put("user ---- row :",row);

        return JSONObject.toJSONString(orderMap);
    }


}
