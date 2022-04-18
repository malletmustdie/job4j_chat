package ru.job4j.chat.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import ru.job4j.chat.dto.RoomDto;
import ru.job4j.chat.dto.RoomInfo;
import ru.job4j.chat.model.Room;

public interface RoomService {

    ResponseEntity<Room> savePerson(RoomDto dto);

    ResponseEntity<Void> deleteRoom(Long roomId);

    ResponseEntity<Void> joinRoom(Long roomId, Long personId);

    ResponseEntity<Void> leaveRoom(Long roomId, Long personId);

    ResponseEntity<List<RoomInfo>> findAllRooms();

    ResponseEntity<RoomInfo> findRoomById(Long roomId);

}
