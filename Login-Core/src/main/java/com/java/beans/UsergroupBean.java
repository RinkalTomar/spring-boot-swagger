package com.java.beans;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder(alphabetic = true)
@ApiModel(description = "Captures identifying Information Who can logs into the Great Games Applicaiton")
public class UsergroupBean extends AbstractAuditableBean {

    @JsonIgnore
    String group;

    @JsonProperty("role")
    String displayName;

    @ApiModelProperty(readOnly = true)
    Long totalUserCount;

    @ApiModelProperty(hidden = true)
    Set<UserBean> userByUsergroup;

    Long userCount;

    Set<PermissionsBean> authorities = new HashSet<>();



}
