package com.java.converters;



import com.java.beans.AbstractAuditableBean;
import com.java.entities.AbstractAuditableEntity;
import org.springframework.stereotype.Component;

@Component
public class AbstractAuditableConverter extends BaseConverter {

    public void populateAuditableBeanFromEntity(AbstractAuditableBean bean, AbstractAuditableEntity entity) {

        populateBaseBeanFromEntity(bean, entity);
        bean.setCreatedBy(entity.getCreatedBy());
        bean.setUpdatedBy(entity.getUpdatedBy());
        bean.setIsDeleted(entity.getIsDeleted());

    }

    public void populateAuditableEntityFromBean(AbstractAuditableBean bean, AbstractAuditableEntity entity) {

        populateBaseEntityFromBean(bean, entity);
        entity.setCreatedBy(bean.getCreatedBy());
        entity.setUpdatedBy(bean.getUpdatedBy());
        entity.setIsDeleted(bean.getIsDeleted());
    }
}
