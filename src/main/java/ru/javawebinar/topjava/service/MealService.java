package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.List;

public interface MealService {
    Meal save(Meal meal);

    void delete(int mealId, int userId) throws NotFoundException;

    List<Meal> getAll(int userId);

    Meal get(int mealId, int id);
}