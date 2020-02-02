package com.java.converters;


import com.java.beans.PermissionsBean;
import com.java.entities.Permissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class PermissionsConverter extends BaseConverter {

    @Autowired
    UsergroupConverter _usergroupConverter;

    public Set<PermissionsBean> getBeansFromEntities(Set<Permissions> entities) {

        Set<PermissionsBean> beans = new HashSet<>();
        for (Permissions entity : entities)
            beans.add(getBeanFromEntity(entity));
        return beans;
    }

    public PermissionsBean getBeanFromEntity(Permissions entity) {

        PermissionsBean bean = new PermissionsBean();

        if (null != entity) {
            bean.setAuthority(entity.getAuthority());
            bean.setDisplayName(entity.getLabel());

        }
        return bean;
    }

    public Set<Permissions> getEntitiesFromBeans(Set<PermissionsBean> beans) {

        Set<Permissions> entities = new HashSet<>();

        for (PermissionsBean bean : beans)
            entities.add(getEntityFromBean(bean));
        return entities;
    }

    public Permissions getEntityFromBean(PermissionsBean bean) {

        Permissions entity = new Permissions();

        if (null != bean) {
            entity.setLabel(bean.getDisplayName());
            entity.setAuthority(bean.getAuthority());
        }
        return entity;
    }

    public Set<Permissions> getEntitiesFromBeansWithoutUserGroup(Set<PermissionsBean> beans) {

        Set<Permissions> entities = new HashSet<>();
        for (PermissionsBean bean : beans)
            entities.add(getEntityFromBeanWithUserGroup(bean));
        return entities;
    }

    private Permissions getEntityFromBeanWithUserGroup(PermissionsBean bean) {

        Permissions entity = new Permissions();
        entity.setLabel(bean.getDisplayName());
        entity.setAuthority(bean.getAuthority());
        return entity;
    }

    public Set<PermissionsBean> getBeansFromEntitiesWithoutUserGroup(Set<Permissions> entities) {

        Set<PermissionsBean> beans = new HashSet<>();
        for (Permissions entity : entities)
            beans.add(getBeanFromEntityWithoutUserGroup(entity));
        return beans;
    }

    private PermissionsBean getBeanFromEntityWithoutUserGroup(Permissions entity) {

        PermissionsBean bean = new PermissionsBean();
        bean.setDisplayName(entity.getLabel());
        bean.setAuthority(entity.getAuthority());
        return bean;
    }
}
