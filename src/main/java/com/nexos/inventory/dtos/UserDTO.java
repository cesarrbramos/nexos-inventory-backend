package com.nexos.inventory.dtos;

import com.nexos.inventory.model.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.function.Function;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserDTO extends AuditableDTO implements Serializable {

    private Long id;
    private String firstname;
    private String lastname;
    private Integer age;
    private LocalDate entryDate;
    private LocalDate birthDate;
    private RoleDTO role;

    public static Function<UserDTO, User> TO_ENTITY = (UserDTO dto) -> {
        var user = new User();
        user.setId(dto.getId());
        user.setFirstname(dto.getFirstname());
        user.setLastname(dto.getLastname());
        user.setEntryDate(dto.getEntryDate());
        user.setCreatedAt(dto.getCreatedAt());
        user.setUpdatedAt(dto.getUpdatedAt());
        user.setBirthDate(dto.getBirthDate());
        user.setRole(RoleDTO.TO_ENTITY.apply(dto.getRole()));
        return user;
    };

    public static Function<User, UserDTO> TO_DTO = (User user) -> {
        var dto = new UserDTO();
        dto.setId(user.getId());
        dto.setFirstname(user.getFirstname());
        dto.setLastname(user.getLastname());
        dto.setEntryDate(user.getEntryDate());
        dto.setCreatedAt(user.getCreatedAt());
        dto.setUpdatedAt(user.getUpdatedAt());
        dto.setBirthDate(user.getBirthDate());
        dto.setRole(RoleDTO.TO_DTO.apply(user.getRole()));

        if (user.getBirthDate() != null) {
            Integer age = user.getBirthDate().until(LocalDate.now()).getYears();
            dto.setAge(age);
        }

        return dto;
    };

    private static final long serialVersionUID = -4115464816543715494L;
}
