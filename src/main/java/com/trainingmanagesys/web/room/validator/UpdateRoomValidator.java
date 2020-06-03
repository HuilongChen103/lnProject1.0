package com.trainingmanagesys.web.room.validator;

import com.trainingmanagesys.web.goods.entity.Goods;
import com.trainingmanagesys.web.room.entity.Room;
import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import java.util.ArrayList;
import java.util.List;

public class UpdateRoomValidator implements DefaultGroupSequenceProvider<Room> {
    @Override
    public List<Class<?>> getValidationGroups(Room room) {
        List<Class<?>> defaultGroupSequence = new ArrayList<>();
        // 指定对象
        defaultGroupSequence.add(Room.class);

        if (null != room){
            if (room.getRoomNum() == null){
                defaultGroupSequence.add(Room.addKeyGroup.class);
                return defaultGroupSequence;
            }

            if (null == room.getAvailable() && null == room.getRoomUsage()){
                defaultGroupSequence.add(Room.updateGroup.class);
            }
        }
        return defaultGroupSequence;
    }
}
