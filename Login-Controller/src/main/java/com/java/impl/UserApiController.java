package com.java.impl;

import com.java.UserApi;
import com.java.beans.UserBean;
import com.java.beans.request.UserAcountResetOrActivateRequest;
import com.java.beans.response.AbstractResponse;
import com.java.beans.response.AbstractResponseBean;
import com.java.beans.search.UserSearchCriteria;
import com.java.enums.Enumeration;
import com.java.exception.BadRequestException;
import com.java.exception.LoginAuthorizationException;
import com.java.services.IUserService;
import io.swagger.annotations.ApiParam;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;


@RestController
public class UserApiController extends AbstractController implements UserApi {


    private final static Logger _log = LogManager.getLogger(UserApi.class);
    @Autowired
    IUserService _userService;

    @Override
    public ResponseEntity<AbstractResponse> addNewUser(@Valid @RequestBody UserBean body) throws Exception {

        Long userID = null;

        Boolean isUpdateRequest = false;

        if (null != body.getId())
            isUpdateRequest = true;

        if (!isUpdateRequest) {
            if (_coreService.hasPermission("USER_WRITE") || _coreService.isSuperAdmin())
                userID = _userService.saveOrUpdate(body);
            else
                throw new LoginAuthorizationException(_message.getMessage("authorization.failed.message"));
        }

        return buildResponse(userID, HttpStatus.CREATED);
    }

    @Override
    @PreAuthorize("hasAuthority('USER_SEARCH')")
    public ResponseEntity<AbstractResponseBean> getAllUsers(@RequestParam(required = false) Map<String, String> searchParams) throws Exception {


        UserSearchCriteria searchCriteria = new UserSearchCriteria();
        AbstractResponseBean<Long, UserBean> responseBean = new AbstractResponseBean<>();
        responseBean.setSuccess(true);
        responseBean.setAuthenticated(_coreService.isAuthenticated());
        parseSearchParams(searchCriteria, searchParams);

        _userService.getallUsers(responseBean, searchCriteria);
        if(responseBean.getObjects().isEmpty())
            responseBean.setMessage(_message.getMessage("no.data.found"));

            return new ResponseEntity<>(responseBean, HttpStatus.OK);
    }

    @Override
    @PreAuthorize("hasAuthority('USER_READ')")
    public ResponseEntity<AbstractResponseBean> getUserDetails(@PathVariable Long id) throws Exception {
        AbstractResponseBean<Long, UserBean> responseBean = new AbstractResponseBean<>();
        UserBean userBean = _userService.getUserById(id);
        responseBean.setObject(userBean);
        responseBean.setSuccess(true);
        responseBean.setAuthenticated(_coreService.isAuthenticated());
        responseBean.setObjectId(userBean.getId());

        return new ResponseEntity<>(responseBean,HttpStatus.OK);
    }
    
    @Override
    public ResponseEntity<AbstractResponseBean> deleteAllUser(@RequestBody List<Long> id) throws Exception {
        AbstractResponseBean<String,UserBean> response = new AbstractResponseBean<String,UserBean>();
        _userService.suspendUser(id);
        response.setSuccess(true);
        return new ResponseEntity<AbstractResponseBean>(response,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AbstractResponseBean> deleteUser(@ApiParam(value = "Unique id of the resource type.", required = true) @PathVariable("id") Long id) throws Exception {
        AbstractResponseBean<String,UserBean> response = new AbstractResponseBean<String,UserBean>();
        if(_coreService.getCurrentUser().getId() == id){
            throw new BadRequestException("Bad Request");
        }
        _userService.deleteUserById(id);
        response.setSuccess(true);
        return new ResponseEntity<AbstractResponseBean>(response,HttpStatus.OK);
    }




    @Override
    public ResponseEntity<AbstractResponseBean> deActiveUser(@RequestBody List<Long> id) throws Exception {
        AbstractResponseBean<String,UserBean> response = new AbstractResponseBean<String,UserBean>();
        _userService.deactivateUser(id);
        response.setObjectId(String.valueOf(id));
        response.setSuccess(true);
        return new ResponseEntity<AbstractResponseBean>(response,HttpStatus.OK);
    }

    private void parseSearchParams(UserSearchCriteria searchCriteria, Map<String, String> searchParams)
    {
        parseCommonSearchParams(searchCriteria, searchParams);
        if(searchParams.get("status") != null)
            searchCriteria.setStatus(Enumeration.UserAccountStatus.valueOf(searchParams.get("status")));
    }
}
