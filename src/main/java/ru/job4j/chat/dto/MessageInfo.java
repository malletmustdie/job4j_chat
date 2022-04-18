package ru.job4j.chat.dto;

import java.time.Instant;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel("Объект ответа со сводной информацией о сообщении")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageInfo {

    @ApiModelProperty("Текст сообщения")
    private String text;

    @ApiModelProperty("Дата сообщения")
    private Instant created;

    @ApiModelProperty("Пользователь отправивший сообщение")
    private String username;

    @ApiModelProperty("Комната в которой опубликовали сообщение")
    private String roomName;

}
