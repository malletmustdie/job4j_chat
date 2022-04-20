package ru.job4j.chat.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.job4j.chat.config.SpringMapperConfig;
import ru.job4j.chat.dto.PersonDto;
import ru.job4j.chat.dto.PersonInfo;
import ru.job4j.chat.model.Person;

@Mapper(config = SpringMapperConfig.class)
public interface PersonMapper {

    @Mapping(source = "dto.id", target = "id")
    @Mapping(source = "dto.username", target = "username")
    @Mapping(source = "dto.password", target = "password")
    @Mapping(source = "dto.roles", target = "roles")
    Person map(PersonDto dto);

    @Mapping(source = "entity.username", target = "username")
    @Mapping(source = "entity.password", target = "password")
    @Mapping(source = "entity.roles", target = "roles")
    PersonDto map(Person entity);

    default PersonInfo toPersonInfo(Person entity) {
        return PersonInfo.builder()
                         .username(entity.getUsername())
                         .roles(entity.getRoles())
                         .totalMessages(entity.getMessages().size())
                         .totalRooms(entity.getRooms().size())
                         .build();
    }

}
