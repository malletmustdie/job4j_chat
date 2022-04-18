package ru.job4j.chat.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.job4j.chat.config.SpringMapperConfig;
import ru.job4j.chat.dto.RoomDto;
import ru.job4j.chat.dto.RoomInfo;
import ru.job4j.chat.model.Room;

@Mapper(config = SpringMapperConfig.class)
public interface RoomMapper {

    @Mapping(source = "dto.name", target = "name")
    Room map(RoomDto dto);

    @Mapping(source = "entity.name", target = "name")
    RoomDto map(Room entity);

    default RoomInfo toRoomInfo(Room entity) {
        return RoomInfo.builder()
                       .name(entity.getName())
                       .totalPersons(entity.getPersons().size())
                       .totalMessages(entity.getMessages().size())
                       .build();
    }

}
