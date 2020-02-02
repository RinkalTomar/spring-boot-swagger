package com.java.converters;
import com.java.beans.UserBean;
import com.java.entities.Users;
import com.java.enums.Enumeration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserConverter extends AbstractAuditableConverter {


    @Autowired
    UsergroupConverter _usergroupConveter;

    public List<UserBean> getBeansFromEntities(List<Users> userEntityList) {

        List<UserBean> beans = new ArrayList<>();
        for (Users entity : userEntityList) {
            beans.add(getBeanFromEntity(entity));
        }
        return beans;
    }

    public UserBean getBeanFromEntity(Users entity) {

        if (null != entity) {
            UserBean bean = new UserBean();

            populateAuditableBeanFromEntity(bean, entity);

            bean.setEmail(entity.getEmail());
            bean.setFirstname(entity.getFirstname());
            bean.setUsername(entity.getUsername());
            bean.setLastname(entity.getLastname());
            bean.setPassword(entity.getPassword());
            bean.setUsergroupId(entity.getUsergroupId());
            bean.setStatus(entity.getStatus());
            bean.setEmailVerified(entity.getEmailVerified());
            try {
                bean.setUsergroup(_usergroupConveter.getBeanFromEntity(entity.getUsergroup(), Enumeration.ResultType.SELECTION));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bean;
        } else
            return null;
    }

    public List<Users> getEntitiesFromBeans(List<UserBean> userBeanList) {

        List<Users> entities = new ArrayList<>();
        for (UserBean bean : userBeanList) {
            entities.add(getEntityfromBean(bean));
        }
        return entities;
    }

    public Users getEntityfromBean(UserBean bean) {


        if(bean != null) {
            Users entity = new Users();
            try {
                populateAuditableEntityFromBean(bean, entity);

                entity.setFirstname(bean.getFirstname());
                entity.setLastname(bean.getLastname());
                entity.setEmail(bean.getEmail());
                entity.setUsername(bean.getUsername());
                entity.setPassword(bean.getPassword());
                entity.setUsergroupId(bean.getUsergroupId());
                entity.setStatus(bean.getStatus());
                entity.setEmailVerified(bean.getEmailVerified());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return entity;
        }
        else
            return null;
    }

    public void populateUserEntityFromBean(UserBean bean, Users entity) {

        entity.setFirstname(bean.getFirstname());
        entity.setLastname(bean.getLastname());
        entity.setEmail(bean.getEmail());
        entity.setUsername(bean.getUsername());
        entity.setUsergroupId(bean.getUsergroupId());
        entity.setEmailVerified(bean.getEmailVerified());
        entity.setStatus(bean.getStatus());
    }

    public void populateUserBeanFromEntity(UserBean userbean, Users userEntity) {

        populateAuditableBeanFromEntity(userbean, userEntity);
        userbean.setEmail(userEntity.getEmail());
        userbean.setFirstname(userEntity.getFirstname());
        userbean.setUsername(userEntity.getUsername());
        userbean.setLastname(userEntity.getLastname());
        userbean.setPassword(userEntity.getPassword());
        userbean.setUsergroupId(userEntity.getUsergroupId());
        userbean.setStatus(userEntity.getStatus());
        userbean.setEmailVerified(userEntity.getEmailVerified());

    }
}
