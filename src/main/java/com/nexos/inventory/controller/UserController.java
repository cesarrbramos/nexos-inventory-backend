package com.nexos.inventory.controller;

import com.nexos.inventory.dtos.UserDTO;
import com.nexos.inventory.enums.Errors;
import com.nexos.inventory.exceptions.CustomException;
import com.nexos.inventory.model.User;
import com.nexos.inventory.service.UserService;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping()
    private ResponseEntity<Page<UserDTO>> getUsers(@QuerydslPredicate(root = User.class) Predicate predicate,
                                                         Pageable pageable) {
        return ResponseEntity.ok(
                userService.findAll(predicate, pageable).map(UserDTO.TO_DTO)
        );
    }

    @PostMapping()
    private ResponseEntity<UserDTO> create(@RequestBody UserDTO dto) {
        User user = UserDTO.TO_ENTITY.apply(dto);
        Optional<User> checkUser = userService.findOneOptional(user.getId());
        if (checkUser.isPresent()) {
            throw new CustomException(HttpStatus.BAD_REQUEST, Errors.USER_ALREADY_EXISTS);
        }
        userService.save(user);
        return ResponseEntity.ok(UserDTO.TO_DTO.apply(user));
    }

    @PutMapping("/{id}")
    private ResponseEntity<UserDTO> update(@RequestBody UserDTO dto, @PathVariable Long id) {
        userService.findOne(id);
        User user = UserDTO.TO_ENTITY.apply(dto);
        userService.save(user);
        return ResponseEntity.ok(
                UserDTO.TO_DTO.apply(user)
        );
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<UserDTO> delete(@PathVariable Long id) {
        User User = userService.findOne(id);
        userService.delete(User);
        return ResponseEntity.ok(
                UserDTO.TO_DTO.apply(User)
        );
    }

}
