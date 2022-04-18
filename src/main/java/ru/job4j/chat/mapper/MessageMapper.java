package ru.job4j.chat.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.job4j.chat.config.SpringMapperConfig;
import ru.job4j.chat.dto.MessageDto;
import ru.job4j.chat.dto.MessageInfo;
import ru.job4j.chat.model.Message;

@Mapper(config = SpringMapperConfig.class)
public interface MessageMapper {

    @Mapping(source = "dto.id", target = "id")
    @Mapping(source = "dto.text", target = "text")
    @Mapping(source = "dto.created", target = "created")
    Message toEntity(MessageDto dto);

    default MessageInfo toMessageInfo(Message entity) {
        return MessageInfo.builder()
                          .text(entity.getText())
                          .created(entity.getCreated())
                          .username(entity.getPerson().getUsername())
                          .roomName(entity.getRoom().getName())
                          .build();
    }

}
