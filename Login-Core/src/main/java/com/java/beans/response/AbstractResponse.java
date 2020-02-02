package com.java.beans.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AbstractResponse {

    private Boolean authenticated = Boolean.FALSE;
    private Boolean success = Boolean.FALSE;
    private String message;
    private Object data;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Map<String, String> error = new HashMap<>();


    public AbstractResponse(Boolean authenticated, Boolean success) {
        this.authenticated = authenticated;
        this.success = success;
    }

    public AbstractResponse(Boolean success, Boolean authenticated, Map<String, String> errorMap) {
        this.success = success;
        this.authenticated = authenticated;
        this.error = errorMap;
    }

    public AbstractResponse(Boolean success, Boolean authenticated, String message) {
        this.success = success;
        this.message = message;
        this.authenticated = authenticated;
    }

    public AbstractResponse(Boolean success, Boolean authenticated, Object data) {
        this.authenticated = authenticated;
        this.data = data;
        this.success = success;
    }


    public AbstractResponse(Boolean success, Boolean authenticated, String message, Object data) {
        this.authenticated = authenticated;
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public AbstractResponse(Boolean success, Boolean authenticated, String message, Map<String, String> errorMap) {
        this.authenticated = authenticated;
        this.success = success;
        this.message = message;
        this.error = errorMap;
    }


}
