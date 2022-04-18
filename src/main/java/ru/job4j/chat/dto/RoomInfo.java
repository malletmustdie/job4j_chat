package ru.job4j.chat.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel("Объект ответа со сводной информацией о комнате")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoomInfo {

    @ApiModelProperty("Наименование комнаты")
    private String name;

    @ApiModelProperty("Общее количество пользователей в комнате")
    private Integer totalPersons;

    @ApiModelProperty("Общее количество сообщений в комнате")
    private Integer totalMessages;

}
