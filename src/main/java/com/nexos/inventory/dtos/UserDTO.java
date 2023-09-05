package com.nexos.inventory.dtos;

import com.nexos.inventory.model.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.function.Function;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserDTO extends AuditableDTO {

    private Long id;
    private String firstname;
    private String lastname;
    private Integer years;
    private LocalDate entryDate;

    public static Function<UserDTO, User> TO_ENTITY = (UserDTO dto) -> {
        var user = new User();
        user.setId(dto.getId());
        user.setFirstname(dto.getFirstname());
        user.setLastname(dto.getLastname());
        user.setEntryDate(dto.getEntryDate());
        user.setCreatedAt(dto.getCreatedAt());
        user.setUpdatedAt(dto.getUpdatedAt());
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

        if (user.getBirthDate() != null) {
            Integer years = LocalDate.now().until(user.getBirthDate()).getYears();
            dto.setYears(years);
        }

        return dto;
    };

}
