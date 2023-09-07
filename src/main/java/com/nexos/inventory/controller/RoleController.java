package com.nexos.inventory.controller;

import com.nexos.inventory.dtos.RoleDTO;
import com.nexos.inventory.model.Role;
import com.nexos.inventory.service.RoleService;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/roles")
public class RoleController {

    private final RoleService roleService;


    @GetMapping()
    private ResponseEntity<Page<RoleDTO>> getRoles(@QuerydslPredicate(root = Role.class) Predicate predicate,
                                                   Pageable pageable) {
        return ResponseEntity.ok(
                roleService.findAll(predicate, pageable).map(RoleDTO.TO_DTO)
        );
    }

    @PostMapping()
    private ResponseEntity<RoleDTO> create(@RequestBody RoleDTO dto) {
        Role Role = RoleDTO.TO_ENTITY.apply(dto);
        dto.setId(null);
        roleService.save(Role);
        return ResponseEntity.ok(RoleDTO.TO_DTO.apply(Role));
    }

    @PutMapping("/{id}")
    private ResponseEntity<RoleDTO> update(@RequestBody RoleDTO dto, @PathVariable Long id) {
        roleService.findOne(id);
        Role Role = RoleDTO.TO_ENTITY.apply(dto);
        roleService.save(Role);
        return ResponseEntity.ok(
                RoleDTO.TO_DTO.apply(Role)
        );
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<RoleDTO> delete(@PathVariable Long id) {
        Role Role = roleService.findOne(id);
        roleService.delete(Role);
        return ResponseEntity.ok(
                RoleDTO.TO_DTO.apply(Role)
        );
    }

}
