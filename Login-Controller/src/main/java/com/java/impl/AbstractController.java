package com.java.impl;

import com.java.beans.response.AbstractResponse;
import com.java.beans.search.AbstractSearchCriteria;
import com.java.enums.Enumeration;
import com.java.message.MessageProperties;
import com.java.services.ICoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public class AbstractController {

    @Autowired
    public ICoreService _coreService;

    @Autowired
    MessageProperties _message;

    public ResponseEntity<AbstractResponse> buildResponse(Object data, HttpStatus status) {
        AbstractResponse response = new AbstractResponse(true, _coreService.isAuthenticated(), data);
        return new ResponseEntity(response, status);
    }

    protected void parseCommonSearchParams(AbstractSearchCriteria searchCriteria, Map<String, String> searchParams)
    {
        if(searchParams.get("search") != null)
        {
            searchCriteria.setSearchKeyword(searchParams.get("search"));
        }

        if(searchParams.get("resultType") != null)
        {
            searchCriteria.setResultType(Enumeration.ResultType.valueOf(searchParams.get("resultType")));
        }

        if(searchParams.get("pageNumber") != null)
        {
            searchCriteria.setPageNumber(Integer.parseInt(searchParams.get("pageNumber")));
        }

        if(searchParams.get("resultPerPage") != null)
        {
            searchCriteria.setResultPerPage(Integer.parseInt(searchParams.get("resultPerPage")));
        }

        if(searchParams.get("sortBy") != null)
        {
            searchCriteria.setSortBy(searchParams.get("sortBy"));
        }

        if(searchParams.get("sortOrder") != null)
        {
            searchCriteria.setSortOrder(searchParams.get("sortOrder"));
        }
    }

    public ResponseEntity<AbstractResponse> buildResponse(String Message, HttpStatus status) {
        AbstractResponse response = new AbstractResponse(true, _coreService.isAuthenticated(), Message);
        return new ResponseEntity(response, status);
    }

    public ResponseEntity<AbstractResponse> buildResponse(String message, Object data, HttpStatus status) {
        AbstractResponse response = new AbstractResponse(true, _coreService.isAuthenticated(), message, data);
        return new ResponseEntity(response, status);
    }

    public ResponseEntity<AbstractResponse> buildResponse(HttpStatus status) {
        AbstractResponse response = new AbstractResponse(true, _coreService.isAuthenticated());
        return new ResponseEntity<>(response, status);
    }


}
