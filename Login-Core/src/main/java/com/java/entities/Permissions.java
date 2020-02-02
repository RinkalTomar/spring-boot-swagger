package com.java.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
@Getter
@Setter
public class Permissions extends BaseEntity {

    @Column(name = "name")
    String authority;

    String label;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "authorities")
    Set<Usergroup> usergroupByAuthority;
}
