package com.java.converters;

import com.java.beans.BaseBean;
import com.java.entities.BaseEntity;
import org.springframework.stereotype.Component;

@Component
public class BaseConverter {


    public void populateBaseBeanFromEntity(BaseBean bean, BaseEntity entity) {
        bean.setId(entity.getId());
        bean.setCreationDatetime(entity.getCreationDatetime());
        bean.setUpdateDatetime(entity.getUpdateDatetime());

    }

    public void populateBaseEntityFromBean(BaseBean bean, BaseEntity entity) {
        entity.setId(bean.getId());
        entity.setCreationDatetime(bean.getCreationDatetime());
        entity.setUpdateDatetime(bean.getUpdateDatetime());
    }
}
