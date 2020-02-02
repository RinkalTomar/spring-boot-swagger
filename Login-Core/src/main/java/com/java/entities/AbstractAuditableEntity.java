package com.java.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;


@Getter
@Setter
@MappedSuperclass
public class AbstractAuditableEntity extends BaseEntity {


    Long createdBy;
    Long updatedBy;
    Boolean isDeleted;

    @PrePersist
    void ifDeleted()
    {
        if(null == isDeleted)
            this.isDeleted = false;
    }
}
