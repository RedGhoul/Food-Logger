package com.somethingsblog.jjfood.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "app_user")
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true)
    private String username;
    @Column(unique = true)
    private String email;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<AppRole> roles = new ArrayList<>();

    @OneToMany(mappedBy = "appUser", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FoodEntry> foodEntries = new ArrayList<>();

    @OneToMany(mappedBy = "appUser", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Food> foods = new ArrayList<>();

    public List<Food> getFoods() {
        return foods;
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }

    public List<FoodEntry> getFoodEntries() {
        return foodEntries;
    }

    public void setFoodEntries(List<FoodEntry> foodEntries) {
        this.foodEntries = foodEntries;
    }


    public AppUser(String name, String username, String email,
                   String password, Collection<AppRole> roles) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public AppUser() {

    }

    public Long getId() {
        return id;
    }

    public AppUser setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public AppUser setName(String name) {
        this.name = name;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public AppUser setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public AppUser setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public AppUser setPassword(String password) {
        this.password = password;
        return this;
    }

    public Collection<AppRole> getRoles() {
        return roles;
    }

    public AppUser setRoles(Collection<AppRole> roles) {
        this.roles = roles;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AppUser appUser)) return false;
        return Objects.equals(getId(), appUser.getId()) && Objects.equals(getName(), appUser.getName()) && Objects.equals(getUsername(), appUser.getUsername()) && Objects.equals(getEmail(), appUser.getEmail()) && Objects.equals(getPassword(), appUser.getPassword()) && Objects.equals(getRoles(), appUser.getRoles());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getUsername(), getEmail(), getPassword(), getRoles());
    }
}
