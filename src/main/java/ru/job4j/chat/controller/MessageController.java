package ru.job4j.chat.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.chat.exceptions.NoSuchModelException;
import ru.job4j.chat.model.Message;
import ru.job4j.chat.model.Room;
import ru.job4j.chat.model.User;
import ru.job4j.chat.service.MessageService;
import ru.job4j.chat.service.RoomService;
import ru.job4j.chat.service.UserService;
import java.util.List;

@RestController
public class MessageController {
    private RoomService roomService;
    private UserService userService;
    private MessageService messageService;

    public MessageController(RoomService roomService, UserService userService, MessageService messageService) {
        this.roomService = roomService;
        this.userService = userService;
        this.messageService = messageService;
    }

    @GetMapping("/rooms/{id}/messages")
    public List<Message> getAllByRoom(@PathVariable Integer id) {
        Room room = roomService.findById(id).orElseThrow(() -> new NoSuchModelException("Room id does not exist"));
        return room.getMessages();
    }

    @PostMapping("/users/{id}/rooms/{rId}/messages")
    public ResponseEntity<Message> create(@PathVariable("id") Integer id, @PathVariable("rId") Integer rId,
                                          @RequestBody Message message) {
        User user = userService.findById(id).orElseThrow(() -> new NoSuchModelException("User id dose not exist"));
        Room room = roomService.findById(rId).orElseThrow(() -> new NoSuchModelException("Room id does not exist"));
        message.setAuthor(user);
        message.setRoom(room);
        return new ResponseEntity<>(
                messageService.saveOrUpdate(message),
                HttpStatus.CREATED
        );
    }


}
