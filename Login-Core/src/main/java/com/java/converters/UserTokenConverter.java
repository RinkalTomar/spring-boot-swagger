package com.java.converters;

import com.java.beans.UserTokenBean;
import com.java.entities.UserToken;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserTokenConverter extends AbstractAuditableConverter{

    private static final Logger _log = LogManager.getLogger(UserTokenConverter.class);

    @Autowired
    UserConverter _userConverter;

    public UserToken getEntityFromBean(UserTokenBean bean)
    {
        UserToken entity = new UserToken();

        populateAuditableEntityFromBean(bean, entity);
        entity.setIsExpired(bean.getIsExpired());
        entity.setToken(bean.getToken());
        entity.setToken_expiry(bean.getToken_expiry());
        entity.setUserId(bean.getUserId());
        entity.setTokenType(bean.getTokenType());
        try{
            entity.setUser(_userConverter.getEntityfromBean(bean.getUser()));
        }catch (Exception e)
        {
            _log.error(e.getMessage());
        }
        return entity;
    }

    public UserTokenBean getBeanFromEntity(UserToken entity) {

        UserTokenBean bean = new UserTokenBean();
        populateAuditableBeanFromEntity(bean, entity);
        bean.setToken_expiry(entity.getToken_expiry());
        bean.setIsExpired(entity.getIsExpired());
        bean.setToken(entity.getToken());
        bean.setUserId(entity.getUserId());

        bean.setUser(_userConverter.getBeanFromEntity(entity.getUser()));

        return bean;
    }

}
