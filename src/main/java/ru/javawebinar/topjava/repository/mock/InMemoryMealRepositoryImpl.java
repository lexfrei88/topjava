package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

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
        MealsUtil.MEALS.forEach(this::save);
    }

    @Override
    public Meal save(Meal meal) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
        }
        repository.put(meal.getId(), meal);
        return meal;
    }

    @Override
    public boolean delete(int id, int userId) {
        return isBelongToOwner(get(id, userId), userId) && repository.remove(id) != null;
    }

    @Override
    public Meal get(int id, int userId) {
        Meal meal = repository.get(id);
        if (meal != null && !isBelongToOwner(meal, userId))
            meal = null;
        return meal;
    }

    @Override
    public List<Meal> getAll(int userId) {
        List<Meal> allMeals = repository.values().stream()
                .filter(meal -> isBelongToOwner(meal, userId))
                .sorted(MealsUtil.comparatorByDateTimeDesc)
                .collect(Collectors.toList());
        return allMeals.isEmpty() ? Collections.emptyList() : allMeals;
    }

    @Override
    public boolean isBelongToOwner(Meal meal, int userId) {
        return (meal != null && meal.getUserId() != null) &&
                Objects.equals(meal.getUserId(), userId);
    }
}

