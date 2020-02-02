package com.java.beans.search;

import com.java.enums.Enumeration;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class AbstractSearchCriteria {

    private List<Long> selectedIds = new ArrayList<>();

    private String searchKeyword;
    private LocalDate fromDate;
    private LocalDate toDate;

    private String sortBy;
    private String sortOrder = "ASC";

    private Integer pageNumber = 0;
    private Integer resultPerPage = 100000;

    private Enumeration.ResultType resultType = Enumeration.ResultType.FULL;

    private boolean reports = false;
}
