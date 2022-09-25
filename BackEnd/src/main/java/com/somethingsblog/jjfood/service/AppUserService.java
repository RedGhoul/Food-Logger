package com.somethingsblog.jjfood.service;

import com.somethingsblog.jjfood.entity.AppRole;
import com.somethingsblog.jjfood.entity.AppUser;

import java.util.List;

public interface AppUserService {
    AppUser saveUser(AppUser user);
    AppRole saveRole(AppRole role);
    void addRoleToUser(String username, String roleName);
    AppUser getUser(String username);
    List<AppUser> getUsers();
}
