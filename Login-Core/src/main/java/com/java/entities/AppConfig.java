package com.java.entities;


import com.java.enums.Enumeration;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@Getter
@Setter
public class AppConfig extends BaseEntity{


    @Enumerated(EnumType.STRING)
    Enumeration.ConfigKeys configKey;
    String configValue;

}
