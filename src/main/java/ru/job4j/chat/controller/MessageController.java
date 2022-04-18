package ru.job4j.chat.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.chat.dto.MessageDto;
import ru.job4j.chat.dto.MessageInfo;
import ru.job4j.chat.model.Message;
import ru.job4j.chat.service.MessageService;
import ru.job4j.chat.util.ApiPathConstants;

@Api(tags = "Эндпоинты для управления сообщениями")
@RestController
@RequestMapping(ApiPathConstants.API_V_1 + ApiPathConstants.MESSAGE)
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @ApiOperation("Отправить сообщение")
    @PostMapping(value = ApiPathConstants.CREATE_MESSAGE, produces = "application/json")
    public ResponseEntity<Message> createMessage(@RequestBody MessageDto messageDto) {
        return messageService.createMessage(messageDto);
    }

    @ApiOperation("Редактировать сообщение")
    @PutMapping(value = ApiPathConstants.UPDATE_MESSAGE, produces = "application/json")
    public ResponseEntity<Void> updateMessage(@RequestBody MessageDto messageDto) {
        return messageService.updateMessage(messageDto);
    }

    @ApiOperation("Удалить сообщение")
    @DeleteMapping(value = ApiPathConstants.DELETE_MESSAGE, produces = "application/json")
    public ResponseEntity<Void> deleteMessage(@RequestParam("messageId") Long id) {
        return messageService.deleteMessage(id);
    }

    @ApiOperation("Получение сообщение идентификатору")
    @GetMapping(value = ApiPathConstants.FIND_MESSAGE, produces = "application/json")
    public ResponseEntity<MessageInfo> findMessageById(@RequestParam("messageId") Long id) {
        return messageService.findMessageById(id);
    }

}
