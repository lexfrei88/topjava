package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * GKislin
 * 15.09.2015.
 */
@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);
    {
        MealsUtil.MEALS.forEach(meal -> save(meal, 1));
    }

    @Override
    public Meal save(Meal meal, int userId) {
        if (meal.getUserId() == null) {
            meal.setUserId(userId);
        }
        if (isBelongToOwner(meal, userId)) {
            if (meal.isNew()) {
                meal.setId(counter.incrementAndGet());
            }
            repository.put(meal.getId(), meal);
            return meal;
        } else {
            return null;
        }
    }

    @Override
    public boolean delete(int id, int userId) {
        return get(id, userId) != null && repository.remove(id) != null;
    }

    @Override
    public Meal get(int id, int userId) {
        Meal meal = repository.get(id);
        return isBelongToOwner(meal, userId) ? meal : null;
    }

    @Override
    public List<Meal> getAll(int userId) {
        List<Meal> allMeals = repository.values().stream()
                .filter(meal -> isBelongToOwner(meal, userId))
                .sorted(MealsUtil.comparatorByDateTimeDesc)
                .collect(Collectors.toList());
        return allMeals.isEmpty() ? Collections.emptyList() : allMeals;
    }

    public List<Meal> getFilteredByDate(LocalDate startDate, LocalDate endDate, int userId) {
        List<Meal> filteredList;
        if ((startDate == null || endDate == null)) {
            filteredList = getAll(userId);
        } else {
            filteredList = getAll(userId).stream()
                    .filter(meal -> DateTimeUtil.isBetween(meal.getDate(), startDate, endDate))
                    .collect(Collectors.toList());
        }
        return filteredList;
    }

    @Override
    public boolean isBelongToOwner(Meal meal, int userId) {
        return (meal != null && meal.getUserId() != null) &&
                Objects.equals(meal.getUserId(), userId);
    }
}

