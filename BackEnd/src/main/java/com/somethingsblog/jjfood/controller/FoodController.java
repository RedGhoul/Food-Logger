package com.somethingsblog.jjfood.controller;

import com.somethingsblog.jjfood.entity.Food;
import com.somethingsblog.jjfood.error.TargetNotFoundException;
import com.somethingsblog.jjfood.service.FoodService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class FoodController {

    private final Logger LOGGER = LoggerFactory.getLogger(FoodController.class);
    private final FoodService foodService;

    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @PostMapping("/food")
    public Food saveFood(@Valid @RequestBody Food food){
        LOGGER.info("Saving " + food.toString());
        return foodService.saveFood(food);
    }

    @GetMapping("/foods")
    public List<Food> getFoods(){
        LOGGER.info("get all targets");
        return foodService.getFoods();
    }

    @GetMapping("/food/{id}")
    public Food getFoodById(@PathVariable("id") Long id) throws TargetNotFoundException {
        LOGGER.info("get single target");
        return foodService.getFoodById(id);
    }

    @DeleteMapping("/food/{id}")
    public boolean deleteFoodById(@PathVariable("id") Long id) throws TargetNotFoundException {
        LOGGER.info("delete single target with id " + id);
        return foodService.deleteFoodById(id);
    }

    @PutMapping ("/food/{id}")
    public Food updateDepartmentById(@PathVariable("id") Long id, @Valid @RequestBody Food food){
        LOGGER.info("update single department");
        return foodService.updateFoodById(id, food);
    }

}
