package com.java.dao.hibernate;

import com.java.beans.search.UserSearchCriteria;
import com.java.entities.Users;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserDao extends AbstractDao<Users>{

    public UserDao() { super(Users.class); }


    public Users findUserForLogin(String username) throws Exception
    {
        StringBuilder hql= new StringBuilder();

        hql.append("SELECT distinct u FROM Users u");
        hql.append(" WHERE 1=1");
        hql.append(" AND (");
        hql.append(" u.email=:username ");
        hql.append("OR u.username = :username )");

        TypedQuery<Users> query = getSession().createQuery(hql.toString(), Users.class);

        query.setParameter("username", username);

        return query.getResultList().get(0);

    }


    public boolean existsByUsername(String email) {

        StringBuilder hql = new StringBuilder();

        hql.append(" SELECT distinct user FROM ");

        hql.append(" Users user ");
        hql.append(" WHERE 1=1 ");
        hql.append(" AND ");
        hql.append(" user.email=:email");

        TypedQuery<Users> query = getSession().createQuery(hql.toString(), Users.class);

        query.setParameter("email", email);

        return query.getResultList().isEmpty()  ? false : true;

    }

    public boolean existsByEmail(String email) throws Exception {

        return  getUsersByEmail(email)== null ? false : true;

    }

    public Users getUsersByEmail(String email) throws Exception {
        StringBuilder hql = new StringBuilder();

        hql.append(" SELECT distinct user FROM ");

        hql.append(" Users user ");
        hql.append(" WHERE 1=1 ");
        hql.append(" AND ");
        hql.append(" user.email=:email ");

        TypedQuery<Users> query = getSession().createQuery(hql.toString(), Users.class);

        query.setParameter("email", email);

        return query.getResultList().stream().findFirst().orElse(null);
    }

    public int count(UserSearchCriteria searchCriteria) {
        int totalRows = 0;
        TypedQuery<Long> query = getSearchQuery(searchCriteria, true,  Long.class);
        totalRows = query.getResultList().get(0).intValue();
        return totalRows;
    }

    public List<Users> search(UserSearchCriteria searchCriteria, boolean isExportRequest) {
        TypedQuery<Users> query = getSearchQuery(searchCriteria, false, Users.class);
        if (!isExportRequest) {
            addPagingParameters(query, searchCriteria);
        }
        return query.getResultList();
    }

    public <T> TypedQuery<T> getSearchQuery(UserSearchCriteria searchCriteria, boolean isCountQuery,  Class<T> type) {
        // standard fields
        Boolean isSearchKeyword = Boolean.FALSE;
        Map<String, Object> paramsMap = new HashMap<>();

        SearchDaoHelper<T> daoHelper = new SearchDaoHelper<T>();

        StringBuffer hql = null;
        if (isCountQuery) {
            hql = new StringBuffer("SELECT count(distinct user.id)");
        }
        else {
            hql = new StringBuffer("SELECT distinct user");
        }

        hql.append(" FROM Users user");
        hql.append(" where 1=1 ");

        if (searchCriteria != null && StringUtils.isNotBlank(searchCriteria.getSearchKeyword())) {
            isSearchKeyword = Boolean.TRUE;
            String[] stringFields = {"firstname", "email", "lastname"};
            String[] numberFields = {};
            hql.append(daoHelper.getSearchWhereStatement(stringFields, numberFields, searchCriteria.getSearchKeyword(),
                    true));
        }

        if (daoHelper.isCriteriaListIsEmpty(searchCriteria.getSelectedIds()) == false) {
            {
                hql.append(" AND user.id IN (:selectedIds)");
                paramsMap.put("selectedIds", searchCriteria.getSelectedIds());
            }

        }

        if (searchCriteria.getStatus() != null)
            hql.append(" AND user.status LIKE %" + searchCriteria.getStatus() + "%");

        if(!searchCriteria.isReports())
            hql.append(" AND user.isDeleted = false OR user.isDeleted = null");

        if(isCountQuery == false && searchCriteria.getSortBy() != null){
            hql.append(" ORDER BY " + searchCriteria.getSortBy() + " " + searchCriteria.getSortOrder());
        }

        TypedQuery<T> q = getSession().createQuery(hql.toString(), type);

        if (isSearchKeyword) {
            daoHelper.setSearchStringValue(q);
        }

        for (Map.Entry<String, Object> parameterEntry : paramsMap.entrySet()) {
            q.setParameter(parameterEntry.getKey(), parameterEntry.getValue());
        }
        return q;

    }
}
