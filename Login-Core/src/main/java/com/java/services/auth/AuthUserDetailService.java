package com.java.services.auth;

import com.java.beans.AppUser;
import com.java.beans.UserBean;
import com.java.converters.UserConverter;
import com.java.dao.hibernate.UserDao;
import com.java.entities.Users;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthUserDetailService implements UserDetailsService{

    @Autowired
    UserDao _userDao;

    @Autowired
    UserConverter _userConverter;


    public static final Logger _log = LogManager.getLogger(AuthUserDetailService.class);

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        try {
            Users user_entity = _userDao.findUserForLogin(s);
            UserBean logged_in_user = _userConverter.getBeanFromEntity(user_entity);
            System.out.println("Fetching User Details");
            if (null == logged_in_user) {
                _log.error("No user is found with username :" + s);
                throw new UsernameNotFoundException(s);
            }
            AppUser authenticatedUser = new AppUser(logged_in_user);
            _log.info("User {} Found.. Logging in as" +  s);
            return authenticatedUser;
        } catch (Exception e) {
            _log.info("Exception Occurred in Fethcing User details for username " + s);
            e.printStackTrace();
        }
        return null;
    }
}

