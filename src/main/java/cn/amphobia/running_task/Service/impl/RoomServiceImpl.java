package cn.amphobia.running_task.Service.impl;

import cn.amphobia.running_task.bean.Room;

import java.util.List;

public interface RoomServiceImpl {

    //创建房间号
    int createRoomNumber(String id,String roomNumber);

    //更新房间状态
    int updateRoomStatus(String status,String roomNumber);

    //返回全部信息
    List<Room> getAllRoom();

    //返回单独房间信息
    Room getOneRoom(String roomNumber);

    //删除房间信息
    int deleteRoom(String roomNumber);
}
