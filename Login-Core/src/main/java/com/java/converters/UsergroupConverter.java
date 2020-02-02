package com.java.converters;


import com.java.beans.UsergroupBean;
import com.java.entities.Usergroup;
import com.java.enums.Enumeration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class UsergroupConverter extends AbstractAuditableConverter {

    @Autowired
    UserConverter _userConverter;


    @Autowired
    PermissionsConverter _permissionConveter;

    public UsergroupBean getBeanFromEntity(Usergroup entity) {
        UsergroupBean bean = new UsergroupBean();
        populateAuditableBeanFromEntity(bean, entity);
        bean.setGroup(entity.getName());
        bean.setDisplayName(entity.getLabel());
        bean.setTotalUserCount(new Long(entity.getUsersByGroup().size()));
        return bean;
    }

    public Set<UsergroupBean> getBeansFromEntities(List<Usergroup> entities, Enumeration.ResultType resultType) {
        HashSet<UsergroupBean> beans = new HashSet<>();
        for (Usergroup entity : entities) {
            beans.add(getBeanFromEntity(entity, resultType));
        }

        return beans;
    }

    public UsergroupBean getBeanFromEntity(Usergroup entity, Enumeration.ResultType resultType)
    {
        UsergroupBean bean;

        bean = getBeanFromEntity(entity);

        if(resultType.equals(Enumeration.ResultType.FULL) || resultType.equals(Enumeration.ResultType.SELECTION))
        {
            try {
                bean.setAuthorities(_permissionConveter.getBeansFromEntitiesWithoutUserGroup(entity.getAuthorities()));
            } catch (Exception e) {
            }
        }

        return bean;
    }
}
