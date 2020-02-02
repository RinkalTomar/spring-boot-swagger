package com.java.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by ongraph on 10/10/18.
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class BaseBean {

    Long id;

    @ApiModelProperty(hidden = true)
    @JsonIgnore
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    Date creationDatetime;

    @ApiModelProperty(hidden = true)
    @JsonIgnore
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    Date updateDatetime;

}