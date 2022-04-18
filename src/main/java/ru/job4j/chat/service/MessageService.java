package ru.job4j.chat.service;

import org.springframework.http.ResponseEntity;
import ru.job4j.chat.dto.MessageDto;
import ru.job4j.chat.dto.MessageInfo;
import ru.job4j.chat.model.Message;

public interface MessageService {

    ResponseEntity<Message> createMessage(MessageDto messageDto);

    ResponseEntity<Void> updateMessage(MessageDto messageDto);

    ResponseEntity<Void> deleteMessage(Long messageId);

    ResponseEntity<MessageInfo> findMessageById(Long messageId);

}
