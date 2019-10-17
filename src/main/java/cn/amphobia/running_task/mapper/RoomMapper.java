package cn.amphobia.running_task.mapper;

import cn.amphobia.running_task.bean.Room;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomMapper {

    //创建房间号
    int createRoomNumber(@Param("id") String id,
                         @Param("roomNumber") String roomNumber);

    //更新房间状态
    int updateRoomStatus(@Param("status") String status,
                         @Param("roomNumber") String roomNumber);

    //返回全部信息
    List<Room> getAllRoom();

    //返回单独房间信息
    Room getOneRoom(@Param("roomNumber") String roomNumber);

    //删除房间信息
    int deleteRoom(@Param("roomNumber") String roomNumber);

}
