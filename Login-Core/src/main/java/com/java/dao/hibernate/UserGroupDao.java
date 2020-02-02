package com.java.dao.hibernate;

import com.java.beans.search.UserGroupSearchCriteria;
import com.java.entities.Usergroup;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserGroupDao extends AbstractDao<Usergroup>{


    public UserGroupDao() {
        super(Usergroup.class);
    }

    public int count(UserGroupSearchCriteria searchCriteria) {
        int totalRows = 0;
        TypedQuery<Long> query = getSearchQuery(searchCriteria, true,  Long.class);
        totalRows = query.getResultList().get(0).intValue();
        return totalRows;
    }

    public List<Usergroup> search(UserGroupSearchCriteria searchCriteria, boolean isExportRequest) {
        TypedQuery<Usergroup> query = getSearchQuery(searchCriteria, false, Usergroup.class);
        if (!isExportRequest) {
            addPagingParameters(query, searchCriteria);
        }
        return query.getResultList();
    }


    private <T> TypedQuery<T> getSearchQuery(UserGroupSearchCriteria searchCriteria, boolean isCountQuery, Class<T> type) {
        // standard fields
        Boolean isSearchKeyword = Boolean.FALSE;
        Map<String, Object> paramsMap = new HashMap<>();

        SearchDaoHelper<T> daoHelper = new SearchDaoHelper<T>();

        StringBuffer hql = null;
        if (isCountQuery) {
            hql = new StringBuffer("SELECT count(distinct usergroup.id)");
        }
        else {
            hql = new StringBuffer("SELECT distinct usergroup");
        }

        hql.append(" FROM Usergroup usergroup");
        hql.append(" where 1=1 ");

        if (searchCriteria != null && StringUtils.isNotBlank(searchCriteria.getSearchKeyword())) {
            isSearchKeyword = Boolean.TRUE;
            String[] stringFields = {"label"};
            String[] numberFields = {};
            hql.append(daoHelper.getSearchWhereStatement(stringFields, numberFields, searchCriteria.getSearchKeyword(),
                    true));
        }

        if (daoHelper.isCriteriaListIsEmpty(searchCriteria.getSelectedIds()) == false) {
            {
                hql.append(" AND usergroup.id IN (:selectedIds)");
                paramsMap.put("selectedIds", searchCriteria.getSelectedIds());
            }

        }

        if(!searchCriteria.isReports())
            hql.append(" AND usergroup.isDeleted = false OR usergroup.isDeleted = null");

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
