package com.java.beans.search;

import com.java.enums.Enumeration;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSearchCriteria extends AbstractSearchCriteria {

    Enumeration.UserAccountStatus status;
}
