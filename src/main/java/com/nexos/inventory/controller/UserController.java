package com.nexos.inventory.controller;

import com.nexos.inventory.dtos.UserDTO;
import com.nexos.inventory.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private final UserService userService;

    @GetMapping()
    private ResponseEntity<List<UserDTO>> getUsers() {
        List<UserDTO> list = userService.findAll().stream()
                .map(UserDTO.TO_DTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

}
