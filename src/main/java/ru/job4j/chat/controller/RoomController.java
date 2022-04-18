package ru.job4j.chat.controller;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.chat.dto.RoomDto;
import ru.job4j.chat.dto.RoomInfo;
import ru.job4j.chat.model.Room;
import ru.job4j.chat.service.RoomService;
import ru.job4j.chat.util.ApiPathConstants;

@Api(tags = "Эндпоинты для управления комнатами")
@RestController
@RequestMapping(ApiPathConstants.API_V_1 + ApiPathConstants.ROOM)
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @ApiOperation("Создание комнаты")
    @PostMapping(value = ApiPathConstants.CREATE_ROOM, produces = "application/json")
    public ResponseEntity<Room> create(@RequestBody RoomDto roomDto) {
        return roomService.savePerson(roomDto);
    }

    @ApiOperation("Удаление комнаты")
    @DeleteMapping(value = ApiPathConstants.DELETE_ROOM, produces = "application/json")
    public ResponseEntity<Void> delete(@RequestParam(name = "roomId") Long id) {
        return roomService.deleteRoom(id);
    }

    @ApiOperation("Получение комнаты по идентификатору")
    @GetMapping(value = ApiPathConstants.FIND_ROOM, produces = "application/json")
    public ResponseEntity<RoomInfo> findRoomById(@RequestParam(name = "personId") Long id) {
        return roomService.findRoomById(id);
    }

    @ApiOperation("Получить все комнаты")
    @GetMapping(value = ApiPathConstants.FIND_ALL_ROOM, produces = "application/json")
    public ResponseEntity<List<RoomInfo>> findAllRooms() {
        return roomService.findAllRooms();
    }

    @ApiOperation("Добавить пользователя в комнату")
    @GetMapping(value = ApiPathConstants.JOIN_ROOM, produces = "application/json")
    public ResponseEntity<Void> joinRoom(@RequestParam(name = "roomId") Long roomId,
                                         @RequestParam(name = "personId") Long personId) {
        return roomService.joinRoom(roomId, personId);
    }

    @ApiOperation("Удалить пользователя из комнаты")
    @GetMapping(value = ApiPathConstants.LEAVE_ROOM, produces = "application/json")
    public ResponseEntity<Void> leaveRoom(@RequestParam(name = "roomId") Long roomId,
                                          @RequestParam(name = "personId") Long personId) {
        return roomService.leaveRoom(roomId, personId);
    }

}
