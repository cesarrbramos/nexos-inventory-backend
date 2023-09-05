package com.nexos.inventory.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AuditableDTO {

    private Date createdAt;
    private Date updatedAt;

}
