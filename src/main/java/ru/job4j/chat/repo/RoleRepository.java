package ru.job4j.chat.repo;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.chat.model.Role;

public interface RoleRepository extends CrudRepository<Role, Integer> {
    Role findByName(String name);
}
