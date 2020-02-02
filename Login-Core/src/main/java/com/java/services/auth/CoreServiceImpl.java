package com.java.services.auth;


import com.java.beans.AppUser;
import com.java.beans.CurrentUser;
import com.java.services.ICoreService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CoreServiceImpl implements ICoreService {

    @Override
    public CurrentUser getCurrentUser() {

        AppUser appUser = null;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            Object authObject = auth.getPrincipal();
            if (authObject != null && authObject instanceof AppUser) {
                appUser = (AppUser) authObject;
            }
        }

        return toCurrentUser(appUser);
    }

    @Override
    public CurrentUser toCurrentUser(AppUser u) {
        return new CurrentUser(u);
    }

    @Override
    public Boolean hasPermission(String permissions) {
        return getCurrentUser().getAuthorities().contains(permissions);
    }


    @Override
    public Boolean isAuthenticated() {
        return SecurityContextHolder.getContext().getAuthentication().isAuthenticated();
    }

    @Override
    public Boolean isCurrentUser(Long id) {
        if ((getCurrentUser() != null) && (getCurrentUser().getId() == id))
            return true;
        else
            return false;
    }

    @Override
    public Boolean isCurrentUser(String username) {
        if ((getCurrentUser() != null) && (getCurrentUser().getUsername().equals(username)))
            return true;
        else
            return false;
    }

    @Override
    public Boolean isSuperAdmin() {
        return getCurrentUser().getUsergroup().equalsIgnoreCase("ROLE_SUPERADMIN");
    }

}
