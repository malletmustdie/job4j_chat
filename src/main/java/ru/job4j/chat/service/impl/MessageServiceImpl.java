package ru.job4j.chat.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.chat.dto.MessageDto;
import ru.job4j.chat.dto.MessageInfo;
import ru.job4j.chat.mapper.MessageMapper;
import ru.job4j.chat.model.Message;
import ru.job4j.chat.repository.MessageRepository;
import ru.job4j.chat.repository.PersonRepository;
import ru.job4j.chat.repository.RoomRepository;
import ru.job4j.chat.service.MessageService;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    private final PersonRepository personRepository;

    private final RoomRepository roomRepository;

    private final MessageMapper messageMapper;

    @Transactional
    @Override
    public ResponseEntity<Message> createMessage(MessageDto messageDto) {
        var message = messageMapper.toEntity(messageDto);
        var person = personRepository.getById(messageDto.getPersonId());
        var room = roomRepository.getById(messageDto.getRoomId());
        message.setPerson(person);
        message.setRoom(room);
        var result = messageRepository.save(message);
        return ResponseEntity.ok(result);
    }

    @Transactional
    @Override
    public ResponseEntity<Void> updateMessage(MessageDto messageDto) {
        var message = messageMapper.toEntity(messageDto);
        messageRepository.findById(message.getId())
                         .ifPresent(messageFromDb -> {
                             messageFromDb.setText(message.getText());
                             messageFromDb.setCreated(message.getCreated());
                             messageFromDb.setPerson(message.getPerson());
                             messageFromDb.setRoom(message.getRoom());
                             messageRepository.save(messageFromDb);
                         });
        return ResponseEntity.ok().build();
    }

    @Transactional
    @Override
    public ResponseEntity<Void> deleteMessage(Long messageId) {
        messageRepository.deleteById(messageId);
        return ResponseEntity.ok().build();
    }

    @Transactional
    @Override
    public ResponseEntity<MessageInfo> findMessageById(Long messageId) {
        return ResponseEntity.ok(
                messageMapper.toMessageInfo(
                        messageRepository.getById(messageId)
                )
        );
    }

}
