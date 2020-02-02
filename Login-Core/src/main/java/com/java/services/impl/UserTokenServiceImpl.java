package com.java.services.impl;



import com.java.beans.UserTokenBean;
import com.java.converters.UserTokenConverter;
import com.java.dao.UserTokenRepository;
import com.java.entities.UserToken;
import com.java.enums.Enumeration;
import com.java.exception.BadRequestException;
import com.java.services.IUserTokenService;
import com.java.utils.AppUtility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;


@Transactional(readOnly = true)
@Service
public class UserTokenServiceImpl extends AbstractService implements IUserTokenService{

    @Autowired
    UserTokenRepository _userTokenRepository;

    @Autowired
    UserTokenConverter _userTokenConverter;

    @Autowired
    UserServiceImpl _userService;

    private static final Logger _log = LogManager.getLogger(UserTokenServiceImpl.class);


    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Long createEmailVerificationTokenRequest(Long user_id) throws Exception{

        String token = AppUtility.generateUUID();
        UserTokenBean tokenBean = new UserTokenBean(token, LocalDateTime.now().plusMonths(1L), Enumeration.UserTokenType.VERIFICATION, user_id);

       UserToken userToken = _userTokenConverter.getEntityFromBean(tokenBean);
       _userTokenRepository.save(userToken);

        return userToken.getId();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Long createPasswordResetRequest(Long user_id, String token) throws Exception{

        UserTokenBean tokenBean = new UserTokenBean(token, LocalDateTime.now().plusMonths(24L), Enumeration.UserTokenType.PASSWORD_RESET,
                user_id);


        return _userTokenRepository.save(_userTokenConverter.getEntityFromBean(tokenBean)).getId();
    }
    @Override
    public UserTokenBean getUserTokenByToken(String token) throws Exception {


        UserToken userToken = _userTokenRepository.getUserTokenByToken(token);
        return userToken!=null?_userTokenConverter.getBeanFromEntity(userToken):null;
    }

    @Override
    public void verifyRequest(String token) throws Exception
    {
        UserTokenBean userToken = getUserTokenByToken(token);
        if (null == userToken) {
            throw new BadRequestException(_message.getMessage("invalid.token"));
        }
        if (userToken.getToken_expiry().isBefore(LocalDateTime.now()) || userToken.getIsExpired())
            throw new BadRequestException(_message.getMessage("expired.token"));
    }

}
