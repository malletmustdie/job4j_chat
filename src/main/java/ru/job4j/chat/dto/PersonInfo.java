package ru.job4j.chat.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.job4j.chat.model.Role;

@ApiModel("Объект ответа со сводной информацией о пользователе")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonInfo {

    @ApiModelProperty("имя пользователя")
    private String username;

    @ApiModelProperty("Роль пользователя")
    private Role role;

    @ApiModelProperty("Общее количество сообщений от пользователя")
    private Integer totalMessages;

    @ApiModelProperty("Общее количество комнат, в которых состоит пользователь")
    private Integer totalRooms;

}
