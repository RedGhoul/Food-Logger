package com.somethingsblog.jjfood.entity;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "food")
public class Food {
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "app_user_id", nullable = false)
    private AppUser appUser;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false, length = 255)
    private String name;

    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToMany(mappedBy = "food", orphanRemoval = true)
    private List<FoodEntry> foodEntries = new ArrayList<>();

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public List<FoodEntry> getFoodEntries() {
        return foodEntries;
    }

    public void setFoodEntries(List<FoodEntry> foodEntries) {
        this.foodEntries = foodEntries;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public Food setId(Long id) {
        this.id = id;
        return this;
    }

    public Food setName(String name) {
        this.name = name;
        return this;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Food setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Food food)) return false;
        return Objects.equals(getId(), food.getId()) && Objects.equals(getName(), food.getName()) && Objects.equals(getCreatedAt(), food.getCreatedAt()) && Objects.equals(getFoodEntries(), food.getFoodEntries());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getCreatedAt(), getFoodEntries());
    }
}
