package ru.job4j.chat.repo;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.chat.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {
}
