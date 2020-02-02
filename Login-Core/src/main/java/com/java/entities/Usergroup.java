package com.java.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Usergroup extends AbstractAuditableEntity {

    String name;
    String label;


    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "usergroupId", targetEntity = Users.class)
    List<Users> usersByGroup;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "usergroup_permission",
            joinColumns = {@JoinColumn(name = "usergroupId", updatable = false, nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "permissionId", updatable = false, nullable = false)}
    )
    Set<Permissions> authorities;
}
