package com.nexos.inventory.dtos;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class AuditableDTO implements Serializable {

    private Date createdAt;
    private Date updatedAt;

    private static final long serialVersionUID = 1516967971776040537L;
}
