package cn.amphobia.running_task.Service;

import cn.amphobia.running_task.Service.impl.RoomServiceImpl;
import cn.amphobia.running_task.bean.Room;
import cn.amphobia.running_task.mapper.RoomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService implements RoomServiceImpl {
    @Autowired
    private RoomMapper roomMapper;

    //创建房间号
    @Override
    public int createRoomNumber(String id,String roomNumber) {
        return roomMapper.createRoomNumber(id,roomNumber);
    }

    //更新房间状态
    @Override
    public int updateRoomStatus(String status, String roomNumber) {
        return roomMapper.updateRoomStatus(status,roomNumber);
    }

    //返回全部信息
    @Override
    public List<Room> getAllRoom() {
        return roomMapper.getAllRoom();
    }

    //返回单独房间信息
    @Override
    public Room getOneRoom(String roomNumber) {
        return roomMapper.getOneRoom(roomNumber);
    }

    //删除房间信息
    @Override
    public int deleteRoom(String roomNumber) {
        return roomMapper.deleteRoom(roomNumber);
    }
}
