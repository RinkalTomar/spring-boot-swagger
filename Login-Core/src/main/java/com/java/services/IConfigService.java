package com.java.services;


import com.java.enums.Enumeration;

public interface IConfigService {

    String getValue(Enumeration.ConfigKeys Key) throws Exception;
}
