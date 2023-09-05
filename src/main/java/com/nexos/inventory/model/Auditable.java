package com.nexos.inventory.model;

import javax.persistence.Basic;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Getter
@Setter
public class Auditable {

    @Basic
    @CreatedDate
    private Date createdAt;

    @Basic
    @LastModifiedDate
    private Date updatedAt;

}
