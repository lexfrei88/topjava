package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

/**
 * Interface for Meal Storage.
 *
 * @author Alex A.
 * @version 1.0
 *          3/25/2017.
 */
public interface MealStorage {
    void create(Meal meal);
    Meal read(int id);
    void update(Meal meal);
    void delete(int id);
    List<Meal> getMeals();
}
