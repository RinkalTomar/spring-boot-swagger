package com.java.services.impl;


import com.java.dao.AppConfigRepository;
import com.java.enums.Enumeration;
import com.java.services.IConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
public class ConfigServiceImpl implements IConfigService {

    @Autowired
    AppConfigRepository _appCofigRepository;

    @Override
    public String getValue(Enumeration.ConfigKeys Key) throws Exception
    {
        return _appCofigRepository.getConfigValue(Key);
    }
}
