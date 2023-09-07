package com.nexos.inventory.dtos;

import com.nexos.inventory.model.Role;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.function.Function;

@Data
@EqualsAndHashCode(callSuper = true)
public class RoleDTO extends AuditableDTO implements Serializable {

    private Long id;
    private String name;


    public static Function<RoleDTO, Role> TO_ENTITY = (RoleDTO dto) -> {
        var role = new Role();
        role.setId(dto.getId());
        role.setName(dto.getName());
        role.setCreatedAt(dto.getCreatedAt());
        role.setUpdatedAt(dto.getUpdatedAt());
        return role;
    };

    public static Function<Role, RoleDTO> TO_DTO = (Role role) -> {
        var dto = new RoleDTO();
        dto.setId(role.getId());
        dto.setName(role.getName());
        dto.setCreatedAt(role.getCreatedAt());
        dto.setUpdatedAt(role.getUpdatedAt());
        return dto;
    };

    private static final long serialVersionUID = -4950006765493265603L;
}
