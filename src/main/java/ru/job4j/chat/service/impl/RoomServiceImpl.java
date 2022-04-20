package ru.job4j.chat.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.chat.dto.RoomDto;
import ru.job4j.chat.dto.RoomInfo;
import ru.job4j.chat.mapper.RoomMapper;
import ru.job4j.chat.model.Room;
import ru.job4j.chat.repository.PersonRepository;
import ru.job4j.chat.repository.RoomRepository;
import ru.job4j.chat.service.RoomService;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    private final PersonRepository personRepository;

    private final RoomMapper roomMapper;

    @Transactional
    @Override
    public ResponseEntity<Room> savePerson(RoomDto dto) {
        var room = roomMapper.map(dto);
        var result = roomRepository.save(room);
        return ResponseEntity.ok(result);
    }

    @Transactional
    @Override
    public ResponseEntity<Void> deleteRoom(Long roomId) {
        var room = roomRepository.findById(roomId)
                                 .orElseThrow(() -> new NullPointerException(
                                         "Room with id " + roomId + " not found!"
                                 ));
        personRepository.findAll()
                        .forEach(person -> person.getRooms()
                                                 .remove(room));
        roomRepository.delete(room);
        return ResponseEntity.ok().build();
    }

    @Transactional
    @Override
    public ResponseEntity<Void> joinRoom(Long roomId, Long personId) {
        var room =
                roomRepository.findById(roomId)
                              .orElseThrow(() -> new NullPointerException(
                                      "Room with id " + roomId + " not found!"
                              ));
        var person =
                personRepository.findById(personId)
                                .orElseThrow(
                                        () -> new NullPointerException(
                                                "Person with id " + personId + " not found!"
                                        )
                                );
        person.addRoom(room);
        return ResponseEntity.ok().build();
    }

    @Transactional
    @Override
    public ResponseEntity<Void> leaveRoom(Long roomId, Long personId) {
        var room =
                roomRepository.findById(roomId)
                              .orElseThrow(() -> new NullPointerException(
                                      "Room with id " + roomId + " not found!"
                              ));
        var person =
                personRepository.findById(personId)
                                .orElseThrow(
                                        () -> new NullPointerException(
                                                "Person with id " + personId + " not found!"
                                        )
                                );
        person.getRooms().remove(room);
        room.getPersons().remove(person);
        return ResponseEntity.ok().build();
    }

    @Transactional
    @Override
    public ResponseEntity<RoomInfo> findRoomById(Long roomId) {
        return ResponseEntity.ok(
                roomMapper.toRoomInfo(
                        roomRepository.findById(roomId)
                                      .orElseThrow(() -> new NullPointerException(
                                              "Room with id " + roomId + " not found!"
                                      ))
                )
        );
    }

    @Transactional
    @Override
    public ResponseEntity<List<RoomInfo>> findAllRooms() {
        return ResponseEntity.ok(
                roomRepository.findAll()
                              .stream()
                              .map(roomMapper::toRoomInfo)
                              .collect(Collectors.toList())
        );
    }

}
