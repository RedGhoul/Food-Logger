package com.somethingsblog.jjfood.repo;

import com.somethingsblog.jjfood.entity.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepo extends JpaRepository<AppRole,Long> {
    AppRole findAppRoleByName(String name);
}

