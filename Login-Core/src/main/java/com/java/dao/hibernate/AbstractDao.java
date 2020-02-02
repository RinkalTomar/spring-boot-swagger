package com.java.dao.hibernate;


import com.java.beans.search.AbstractSearchCriteria;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class AbstractDao<T> {

    private final Class<T> tClass;

    @PersistenceContext
    EntityManager entityManager;



    public AbstractDao (Class<T> type) {
        tClass = type;
    }

    public Session getSession()
    {
        return entityManager.unwrap(Session.class);

    }

    public T getById(Long id) {
        return getSession().get(tClass,id);
    }

    public void save(T entity) throws Exception
    {
         getSession().saveOrUpdate(entity);
    }

    public Boolean existsById(Long id) throws Exception
    {
        return getSession().get(tClass, id) != null?true:false;
    }

    public CriteriaQuery<T> getCriteriaQuery()
    {
        return getSession().getCriteriaBuilder().createQuery(tClass);
    }

    public List<T> findAll()
    {
        CriteriaQuery<T> query = getCriteriaQuery();
        Root<T> root = query.from(tClass);
        query.select(root);
        return getSession().createQuery(query).getResultList();

    }

    protected void addPagingParameters(TypedQuery<T> query, AbstractSearchCriteria searchCriteria)
    {
        query.setFirstResult( (searchCriteria.getPageNumber() *  searchCriteria.getResultPerPage() ));
        query.setMaxResults(searchCriteria.getResultPerPage());
    }

    public void update(T entity) throws Exception
    {
        getSession().update(entity);
    }

    public void update(List<T> entities) throws Exception
    {
        for (T entity:entities)
            getSession().update(entity);
    }

    public void delete(T entity)throws Exception{entityManager.remove(entity);}
}

