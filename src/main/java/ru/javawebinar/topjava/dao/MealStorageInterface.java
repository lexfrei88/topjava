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
public interface MealStorageInterface {
    void create(Meal meal);
    Meal read(Meal meal);
    void update(Meal meal);
    void delete(Meal meal);
    List<Meal> getMeals();
}
