package cn.amphobia.running_task.controller;

import cn.amphobia.running_task.Service.impl.AddressServiceImpl;
import cn.amphobia.running_task.bean.Address;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/address")
public class AddressController {


    @Autowired
    private AddressServiceImpl addressServiceImpl;

    //添加地址
    @GetMapping("addAddress")
    public String addAddress(@RequestParam(value = "address") String address,
                             @RequestParam(value = "status") String status,
                             @RequestParam(value = "telephone") String telephone,
                             @RequestParam(value = "username") String username,
                             @RequestParam(value = "userTelephone") String userTelephone) {
        Map<String, Object> addressMap = new HashMap<>();
        //显示信息
        String msg = "";
        //显示标记
        int successful = 0;

        //设置id
        String id = UUID.randomUUID().toString().replace("-", "").toLowerCase();
        //调用service，添加地址
        int row = addressServiceImpl.addAddress(id, address, status, telephone,username,userTelephone);

        if (row == 1) {
            msg = "添加成功";
            successful = 0;
        } else {
            msg = "添加失败";
            successful = 1;
        }

        //将要输出的信息放到map中
        addressMap.put("msg", msg);
        addressMap.put("successful", successful);

        return JSONObject.toJSONString(addressMap);
    }

    //查看地址
    @GetMapping("getAddress")
    public String getAddress(@RequestParam(value = "userTelephone") String userTelephone) {
        Map<String, Object> addressMap = new HashMap<>();
        //显示信息
        String msg = "";
        //显示标记
        int successful = 0;

        List<Address> address = addressServiceImpl.getAddress(userTelephone);

        if (address.size() == 0) {
            msg = "查询失败";
            successful = 1;
        } else {
            msg = "查询成功";
            successful = 0;
        }
        //将要输出的信息放到map中
        addressMap.put("msg", msg);
        addressMap.put("successful", successful);
        addressMap.put("data", address);

        return JSONObject.toJSONString(addressMap);
    }

    //修改地址
        @GetMapping("updateAddress")
    public String updateAddress(@RequestParam(value = "address") String address,
                                @RequestParam(value = "username") String username,
                                @RequestParam(value = "status") String status,
                                @RequestParam(value = "telephone") String telephone,
                                @RequestParam(value = "id") String id) {
        Map<String, Object> addressMap = new HashMap<>();
        //显示信息
        String msg = "";
        //显示标记
        int successful = 0;

        int row = addressServiceImpl.updateAddress(address, username,status, telephone, id);

        if (row == 1) {
            msg = "修改成功";
            successful = 0;
        } else {
            msg = "修改失败";
            successful = 1;
        }

        //将要输出的信息放到map中
        addressMap.put("msg", msg);
        addressMap.put("successful", successful);

        return JSONObject.toJSONString(addressMap);
    }


}
