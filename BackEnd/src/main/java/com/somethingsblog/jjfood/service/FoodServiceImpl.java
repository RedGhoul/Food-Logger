package com.somethingsblog.jjfood.service;

import com.somethingsblog.jjfood.controller.FoodController;
import com.somethingsblog.jjfood.entity.Food;
import com.somethingsblog.jjfood.error.TargetNotFoundException;
import com.somethingsblog.jjfood.repo.FoodRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodServiceImpl implements FoodService {
    private FoodRepository foodRepository;
    private final Logger LOGGER = LoggerFactory.getLogger(FoodController.class);

    public FoodServiceImpl(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    @Override
    public Food saveFood(Food food) {
        return this.foodRepository.save(food);
    }

    @Override
    public List<Food> getFoods() {
        return foodRepository.findAll();
    }

    @Override
    public Food getFoodById(Long id) throws TargetNotFoundException {
        var target = foodRepository.findById(id);
        if(target.isEmpty()) throw new TargetNotFoundException("Department not found");
        return target.get();
    }

    @Override
    public boolean deleteFoodById(Long id) {
        Food food = null;
        try {
            food = getFoodById(id);
        } catch (TargetNotFoundException e) {
            LOGGER.error("Target Was Not Found", e);
            return false;
        }
        foodRepository.deleteById(food.getId());
        return true;
    }

    @Override
    public Food updateFoodById(Long id, Food food) {
        var targetFromDB = foodRepository.findById(id);
        if(targetFromDB.isPresent()){
            var realTargetFromDB = targetFromDB.get();
            if(food.getName() != null &&
                    !realTargetFromDB.getName().equals(
                            food.getName())){
                realTargetFromDB.setName(food.getName());
            }

            foodRepository.save(realTargetFromDB);
            return realTargetFromDB;
        }
        return null;
    }

    @Override
    public Food fetchTargetByName(String name) throws TargetNotFoundException {
        var target = foodRepository.findFoodByName(name);
        if(target.isEmpty()) throw new TargetNotFoundException("target not found");
        return target.get();
    }
}
