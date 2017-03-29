package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Alex A.
 * @version 1.0
 *          3/25/2017.
 */
public class MealStorageInHashMap implements MealStorage {
    private AtomicInteger idCounter = new AtomicInteger(0);
    private Map<Integer, Meal> meals = new ConcurrentHashMap<>();
    {
        create(new Meal(0, LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500));
        create(new Meal(0, LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000));
        create(new Meal(0, LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500));
        create(new Meal(0, LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000));
        create(new Meal(0, LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500));
        create(new Meal(0, LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510));
    }

    public void create(Meal meal) {
        int id = idCounter.getAndIncrement();
        meals.put(id, new Meal(id, meal.getDateTime(), meal.getDescription(), meal.getCalories()));
    }

    public Meal read(int id) {
        return meals.get(id);
    }

    public void update(Meal meal) {
        if (meal.getId() < 0)
            return;
        Meal oldMeal = meals.get(meal.getId());
        Meal newMeal = new Meal(
                oldMeal.getId(),
                meal.getDateTime() != null ?
                        meal.getDateTime() :
                        oldMeal.getDateTime(),
                meal.getDescription() != null ?
                        meal.getDescription() :
                        oldMeal.getDescription(),
                meal.getCalories() >= 0 ?
                        meal.getCalories() :
                        oldMeal.getCalories()
        );
        meals.put(meal.getId(), newMeal);
    }

    public void delete(int id) {
        meals.remove(id);
    }

    public List<Meal> getMeals() {
        return new ArrayList<>(meals.values());
    }
}
