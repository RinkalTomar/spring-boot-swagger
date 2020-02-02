package com.java.services;

import com.java.beans.PasswordResetBean;
import com.java.beans.UserBean;
import com.java.beans.request.UserAcountResetOrActivateRequest;
import com.java.beans.response.AbstractResponseBean;
import com.java.beans.search.UserSearchCriteria;

import java.util.List;

public interface IUserService {

    Long saveOrUpdate(UserBean body) throws Exception;

    void getallUsers(AbstractResponseBean<Long, UserBean> responseBean, UserSearchCriteria searchCriteria)throws Exception;

    UserBean getUserById(Long id) throws Exception;

    void deactivateUser(List<Long> id) throws Exception;

    void suspendUser(List<Long> id) throws Exception;

    void deleteUserById(Long id)throws Exception;
}
