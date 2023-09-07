package com.nexos.inventory.service;

import com.nexos.inventory.enums.Errors;
import com.nexos.inventory.exceptions.CustomException;
import com.nexos.inventory.model.User;
import com.nexos.inventory.repository.UserRepository;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Page<User> findAll(Predicate predicate, Pageable pageable) {
        return userRepository.findAll(predicate, pageable);
    }

    public User save(User entity) {
        return userRepository.save(entity);
    }

    public User findOne(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, Errors.NOT_USER_FOUND));
    }

    public Optional<User> findOneOptional(Long id) {
        return userRepository.findById(id);
    }

    public void delete(User User) {
        userRepository.delete(User);
    }

}
