package com.java.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@Getter
@Setter
public class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Temporal(TemporalType.TIMESTAMP)
    Date creationDatetime;

    @Temporal(TemporalType.TIMESTAMP)
    Date updateDatetime;


    @PreUpdate
    public void setLastModified() {
        this.updateDatetime = new Date(); }

    @PrePersist
    public void setCreationTime()
    {   this.creationDatetime = new Date(); }
}
