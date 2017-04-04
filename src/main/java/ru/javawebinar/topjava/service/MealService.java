package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;

public interface MealService {
    Meal save(Meal meal, int userId);

    void delete(int mealId, int userId) throws NotFoundException;

    List<Meal> getAll(int userId);

    List<Meal> getFilteredByDate(LocalDate startDate, LocalDate endDate, int userId);

    Meal get(int mealId, int id);
}