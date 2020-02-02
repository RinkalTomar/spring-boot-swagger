package com.java.services.impl;


import com.java.message.MessageProperties;
import com.java.services.IConfigService;
import com.java.services.ICoreService;
import org.springframework.beans.factory.annotation.Autowired;

public class AbstractService {


    @Autowired
    ICoreService _coreService;

    @Autowired
    MessageProperties _message;

    @Autowired
    IConfigService _configService;


}
