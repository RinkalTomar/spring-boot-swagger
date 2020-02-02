package com.java.services;

import com.java.beans.AppUser;
import com.java.beans.CurrentUser;

public interface ICoreService {
    CurrentUser getCurrentUser();

    CurrentUser toCurrentUser(AppUser u);

    Boolean hasPermission(String permissions);

    Boolean isAuthenticated();

    Boolean isCurrentUser(Long id);

    Boolean isCurrentUser(String username);

    Boolean isSuperAdmin();
}
