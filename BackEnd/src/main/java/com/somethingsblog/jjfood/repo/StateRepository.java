package com.somethingsblog.jjfood.repo;

import com.somethingsblog.jjfood.entity.FoodEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StateRepository extends JpaRepository<FoodEntry, Long> {
}