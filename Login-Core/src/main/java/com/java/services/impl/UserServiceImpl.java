package com.java.services.impl;


import com.java.beans.UserBean;
import com.java.beans.response.AbstractResponseBean;
import com.java.beans.search.UserSearchCriteria;
import com.java.constants.Constants;
import com.java.converters.UserConverter;
import com.java.dao.hibernate.UserDao;
import com.java.entities.Users;
import com.java.enums.Enumeration;
import com.java.exception.BadRequestException;
import com.java.exception.LoginAuthorizationException;
import com.java.services.IUserService;
import com.java.services.IUserTokenService;
import com.java.utils.AppUtility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl extends AbstractService implements IUserService{

    private static final Logger _log = LogManager.getLogger(UserServiceImpl.class);

    @Autowired
    UserDao _userRepository;

    @Autowired
    UserConverter _userConverter;

    @Autowired
    IUserTokenService _userTokenService;

    @Override
    @Transactional
    public Long saveOrUpdate(UserBean body) throws Exception {

        if (_userRepository.existsByUsername(body.getUsername()))
            throw new BadRequestException(_message.getMessage("username.exist"));
        else if (_userRepository.existsByEmail(body.getEmail()))
            throw new BadRequestException(_message.getMessage("email.exist"));

        Users newUser = _userConverter.getEntityfromBean(body);
        newUser.setPassword(_configService.getValue(Enumeration.ConfigKeys.TEMP_PASSWORD));
        newUser.setStatus(Enumeration.UserAccountStatus.VERIFICATION_PENDING);
        newUser.setEmailVerified(Boolean.FALSE);

        newUser.setEmailCommunicationToken(AppUtility.generateUUID());
        newUser.setTokenExpiryDate(LocalDateTime.now().plusDays(Constants.INVITE_ACTIVATION_TOKEN_EXPIRES_ON_DAYS));

        _userRepository.save(newUser);

        Long userId =newUser.getId();

        return userId;
    }


    @Override
    public void getallUsers(AbstractResponseBean<Long, UserBean> responseBean, UserSearchCriteria searchCriteria) throws Exception {

        int count = _userRepository.count(searchCriteria);
        List<UserBean> beans = _userConverter.getBeansFromEntities(
                _userRepository.search(searchCriteria, false));

        responseBean.setTotalResultsCount(count);
        responseBean.setResultCountPerPage(beans.size());
        responseBean.setCurrentPageIndex(searchCriteria.getPageNumber());
        responseBean.setObjects(beans);
    }

    @Override
    public UserBean getUserById(Long id) throws Exception {
        UserBean bean =  _userConverter.getBeanFromEntity(_userRepository.getById(id));
        if(bean == null){
            throw new BadRequestException(_message.getMessage("invalid.identifier"));
        }
        return bean;
    }

    private void validateSearchCriteria(UserSearchCriteria searchCriteria) throws Exception
    {
        Long logg_in_user = _coreService.getCurrentUser().getId();
        if(!_coreService.hasPermission("USER_SEARCH"))
        {
                if(!searchCriteria.getSelectedIds().contains(logg_in_user))
                    throw new LoginAuthorizationException();
                else{
                    //Remove additional Ids which are not accessible to the current user.
                    searchCriteria.getSelectedIds().removeIf(i -> i != logg_in_user);
                }

                if(searchCriteria.getSelectedIds().isEmpty())
                    throw new LoginAuthorizationException();
        }
        else{

            //TODO: EXCLUDE SUPERADMIN USER.
        }

    }

    @Override
    public void deactivateUser (List<Long> id) throws Exception {
        UserSearchCriteria searchCriteria = new UserSearchCriteria();
        searchCriteria.getSelectedIds().addAll(id);
        List<Users> userss = _userRepository.search(searchCriteria,false);
        userss.forEach(p -> p.setStatus(Enumeration.UserAccountStatus.INACTIVE));
        _userRepository.update(userss);
    }

    @Override
    public void suspendUser(List<Long> id) throws Exception {
        Long logg_in_user = _coreService.getCurrentUser().getId();
        UserSearchCriteria searchCriteria = new UserSearchCriteria();
        searchCriteria.getSelectedIds().addAll(id);
        searchCriteria.getSelectedIds().removeIf(i -> i == logg_in_user);
        List<Users> userss = _userRepository.search(searchCriteria,false);
        userss.forEach(p -> {p.setIsDeleted(true); p.setStatus(Enumeration.UserAccountStatus.INACTIVE);});
        _userRepository.update(userss);
    }

    @Override
    public void deleteUserById(Long id) throws Exception {
        Users userss = _userRepository.getById(id);
        userss.setIsDeleted(true);
        userss.setStatus(Enumeration.UserAccountStatus.INACTIVE);
        _userRepository.update(userss);
    }


}
