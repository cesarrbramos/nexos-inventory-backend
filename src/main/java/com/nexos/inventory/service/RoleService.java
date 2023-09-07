package com.nexos.inventory.service;

import com.nexos.inventory.enums.Errors;
import com.nexos.inventory.exceptions.CustomException;
import com.nexos.inventory.model.Role;
import com.nexos.inventory.repository.RoleRepository;
import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public Page<Role> findAll(Predicate predicate, Pageable pageable) {
        return roleRepository.findAll(predicate, pageable);
    }

    public Role save(Role entity) {
        return roleRepository.save(entity);
    }

    public Role findOne(Long id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, Errors.NOT_ROLE_FOUND));
    }

    public void delete(Role Role) {
        roleRepository.delete(Role);
    }

}
