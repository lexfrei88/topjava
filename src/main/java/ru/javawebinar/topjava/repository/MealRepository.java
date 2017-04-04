package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDate;
import java.util.List;

/**
 * GKislin
 * 06.03.2015.
 */
public interface MealRepository {
    Meal save(Meal Meal, int userId);

    // false if not found
    boolean delete(int id, int userId);

    // null if not found
    Meal get(int id, int userId);

    List<Meal> getAll(int userId);

    List<Meal> getFilteredByDate(LocalDate startDate, LocalDate endDate, int userId);

    boolean isBelongToOwner(Meal meal, int userId);
}
