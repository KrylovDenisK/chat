package ru.job4j.chat.repo;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.chat.model.Message;

public interface MessageRepository extends CrudRepository<Message, Integer> {
}
