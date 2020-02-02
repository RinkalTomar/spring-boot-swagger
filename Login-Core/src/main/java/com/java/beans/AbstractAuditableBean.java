package com.java.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AbstractAuditableBean extends BaseBean {

    @ApiModelProperty(hidden = true)
    @JsonIgnore
    Long createdBy;

    @ApiModelProperty(hidden = true)
    @JsonIgnore
    Long updatedBy;

    @JsonIgnore
    Boolean isDeleted;

    @PrePersist
    @PreUpdate
    public void checkForDelete() {
        if (null == isDeleted)
            isDeleted = false;
    }

}
