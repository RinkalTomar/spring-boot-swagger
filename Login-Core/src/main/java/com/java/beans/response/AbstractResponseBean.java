package com.java.beans.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AbstractResponseBean<ID, T> extends AbstractResponse{

    private T object;
    private List<T> objects;
    private ID objectId;

    private Integer totalResultsCount;
    private Integer resultCountPerPage;
    private Integer currentPageIndex;
}
