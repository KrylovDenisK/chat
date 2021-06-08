package ru.job4j.chat.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.job4j.chat.exceptions.NoSuchModelException;
import ru.job4j.chat.model.Room;
import ru.job4j.chat.model.User;
import ru.job4j.chat.service.RoomService;
import ru.job4j.chat.service.UserService;

import java.util.ArrayList;
import java.util.List;


@RestController
public class RoomController {
    private RoomService roomService;
    private UserService userService;

    public RoomController(RoomService roomService, UserService userService) {
        this.roomService = roomService;
        this.userService = userService;
    }

    @GetMapping("/rooms")
    public List<Room> findAll()  {
        return roomService.findAll();
    }

    @GetMapping("/rooms/{id}")
    public ResponseEntity<Room> findById(@PathVariable Integer id) {
        Room room = roomService.findById(id).orElseThrow(() -> new NoSuchModelException("Room id does not exist"));
        return new ResponseEntity<>(room, HttpStatus.OK);
    }

    @GetMapping("/users/{id}/rooms")
    public List<Room> findAllByUser(@PathVariable Integer id) {
        User user = userService.findById(id).orElseThrow(() -> new NoSuchModelException("User id dose not exist"));
        return new ArrayList<>(user.getRooms());
    }

    @PostMapping("/users/{id}/rooms")
    public ResponseEntity<Room> createRoom(@PathVariable Integer id, @RequestBody Room room) {
        User user = userService.findById(id).orElseThrow(() -> new NoSuchModelException("User id dose not exist"));
        room.setAuthor(user);
        room.addParticipants(user);
        return new ResponseEntity<>(
                roomService.saveOrUpdate(room),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/users/{id}/rooms")
    public ResponseEntity<Void> updateRoom(@PathVariable Integer id, @RequestBody Room room) {
        User user = userService.findById(id).orElseThrow(() -> new NoSuchModelException("User id dose not exist"));
        Room DBRoom = roomService.findById(room.getId()).orElseThrow(() -> new NoSuchModelException("Room id dose not exist"));
        if (!DBRoom.getAuthor().equals(user)) {
            throw new NoSuchModelException("You are not author for room!!!!");
        }
        roomService.saveOrUpdate(room);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/users/{id}/rooms/{rId}")
    public ResponseEntity<Void> deleteRoom(@PathVariable("id") Integer id, @PathVariable("rId") Integer rId) {
        User user = userService.findById(id).orElseThrow(() -> new NoSuchModelException("User id dose not exist"));
        Room DBRoom = roomService.findById(rId).orElseThrow(() -> new NoSuchModelException("Room id dose not exist"));
        if (!DBRoom.getAuthor().equals(user)) {
            throw new NoSuchModelException("You are not author for room!!!!");
        }
        roomService.delete(DBRoom);
        return ResponseEntity.ok().build();
    }
}
