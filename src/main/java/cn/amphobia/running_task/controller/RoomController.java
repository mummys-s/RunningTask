package cn.amphobia.running_task.controller;


import cn.amphobia.running_task.Service.impl.RoomServiceImpl;
import cn.amphobia.running_task.bean.Room;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/room")
public class RoomController {
    @Autowired
    private RoomServiceImpl roomServiceImpl;

    //加入
    @GetMapping("addAnother")
    public String addAnother() {
        Map<String, Object> roomMap = new HashMap<>();
        List<Room> allRoom = roomServiceImpl.getAllRoom();
        String status = "0";
        String msg = "";
        String roomNumber = "";
        String success = "";
        if (allRoom.size() != 0) {
            for (int i = 0; i < allRoom.size(); i++) {
                status = allRoom.get(i).getStatus();
                roomNumber = allRoom.get(i).getRoomNumber();
                if (status.equals("0")) {
                    msg = "加入房间";
                    success = "2";
                    break;
                }
            }
//            System.out.println("状态:"+status);
//            System.out.println("房间号:"+roomNumber);
            if (status.equals("0")) {
//                System.out.println("1111");
                status = "1";
                roomServiceImpl.updateRoomStatus(status, roomNumber);
            } else {
                //创建随机房间数
                roomNumber = UUID.randomUUID().toString().split("-")[0];
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String create_time = dateFormat.format(new Date()).replace("-", "")
                        .replace(" ", "").replace(":", "");
                String id = roomNumber + create_time;
                int row = roomServiceImpl.createRoomNumber(id, roomNumber);
                if (row == 1) {
                    msg = "房间创建成功";
                    success = "1";
                } else {
                    msg = "房间创建失败";
                    success = "";
                }
            }
        } else {
            //创建随机房间数
            roomNumber = UUID.randomUUID().toString().split("-")[0];
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String create_time = dateFormat.format(new Date()).replace("-", "")
                    .replace(" ", "").replace(":", "");
            String id = roomNumber + create_time;
            int row = roomServiceImpl.createRoomNumber(id, roomNumber);
            if (row == 1) {
                msg = "房间创建成功";
                success = "1";
            } else {
                msg = "房间创建失败";
                success = "";
            }
        }

        roomMap.put("roomNumber", roomNumber);
        roomMap.put("msg", msg);
        roomMap.put("success",success);
        return JSONObject.toJSONString(roomMap);
    }

    //退出
    @GetMapping("deleteRoom")
    public String removeRoom(@RequestParam(value = "roomNumber") String roomNumber) {
        String status = "1";
        String msg = "";
        Room oneRoom = roomServiceImpl.getOneRoom(roomNumber);

        Map<String, Object> roomMap = new HashMap<>();

        if (oneRoom.getStatus().equals("1")){
            status = "0";
            int updateRow = roomServiceImpl.updateRoomStatus(status, roomNumber);
            if (updateRow == 1){
                msg = "退出1位";
            }
        }else if (oneRoom.getRoomNumber().equals(roomNumber) && oneRoom.getStatus().equals("0")){
            System.out.println("---------------");
            int deleteRow = roomServiceImpl.deleteRoom(roomNumber);
            if (deleteRow == 1){
                msg = "退出2位";
            }
        }

        roomMap.put("roomNumber", roomNumber);
        roomMap.put("msg", msg);
        roomMap.put("status",status);
        return JSONObject.toJSONString(roomMap);
    }

    //轮播图
    @GetMapping("getPicture")
    public String getPicture(){
        String[] arrPicture = {
                "http://amphobia.cn/images/lunbo_01.jpg",
                "http://amphobia.cn/images/lunbo_02.jpg",
                "http://amphobia.cn/images/lunbo_03.jpg",
                "http://amphobia.cn/images/lunbo_04.jpg",
        };
        return JSONObject.toJSONString(arrPicture);
    }

    //广告页
    @GetMapping("getAdvance")
    public String getAdvance(){
        String advance = "http://amphobia.cn/images/advance.jpeg";
        return JSONObject.toJSONString(advance);
    }


}
