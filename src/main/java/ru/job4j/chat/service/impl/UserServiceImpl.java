package ru.job4j.chat.service.impl;

import org.springframework.stereotype.Service;
import ru.job4j.chat.model.User;
import ru.job4j.chat.repo.RoleRepository;
import ru.job4j.chat.repo.UserRepository;
import ru.job4j.chat.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public User saveOrUpdate(User model) {
        model.addRole(roleRepository.findByName("ROLE_USER"));
        return userRepository.save(model);
    }

    @Override
    public List<User> findAll() {
        return StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<User> findById(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public void delete(User model) {
        userRepository.delete(model);
    }
}
