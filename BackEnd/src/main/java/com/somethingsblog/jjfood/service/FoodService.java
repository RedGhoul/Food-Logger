package com.somethingsblog.jjfood.service;

import com.somethingsblog.jjfood.entity.Food;
import com.somethingsblog.jjfood.error.TargetNotFoundException;

import java.util.List;

public interface FoodService {
    Food saveFood(Food food);

    List<Food> getFoods();

    Food getFoodById(Long id) throws TargetNotFoundException;

    boolean deleteFoodById(Long id) throws TargetNotFoundException;

    Food updateFoodById(Long id, Food food);
    Food fetchTargetByName(String name) throws TargetNotFoundException;
}
