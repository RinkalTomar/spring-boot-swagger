package com.java.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;

@Component
public class MessageProperties {

    @Autowired
    MessageSourceAccessor _messageSourceAccessor;

    public String getMessage(String property) {
        return _messageSourceAccessor.getMessage(property);
    }
}
