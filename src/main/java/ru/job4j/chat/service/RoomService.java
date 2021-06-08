package ru.job4j.chat.service;

import ru.job4j.chat.model.Room;

import java.util.List;


public interface RoomService extends SimpleService<Room, Integer> {
    List<Room> findByName(String name);
}
