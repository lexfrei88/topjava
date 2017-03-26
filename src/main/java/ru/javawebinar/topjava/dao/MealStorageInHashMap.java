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
public class MealStorageInHashMap implements MealStorageInterface {
    private static AtomicInteger idCounter = new AtomicInteger(0);
    private static Map<Integer, Meal> meals = new ConcurrentHashMap<>();
    static {
        meals.put(idCounter.get(), new Meal(idCounter.getAndIncrement(), LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500));
        meals.put(idCounter.get(), new Meal(idCounter.getAndIncrement(), LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000));
        meals.put(idCounter.get(), new Meal(idCounter.getAndIncrement(), LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500));
        meals.put(idCounter.get(), new Meal(idCounter.getAndIncrement(), LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000));
        meals.put(idCounter.get(), new Meal(idCounter.getAndIncrement(), LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500));
        meals.put(idCounter.get(), new Meal(idCounter.getAndIncrement(), LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510));
    }

    public void create(Meal meal) {
        int id = idCounter.getAndIncrement();
        meals.put(id, new Meal(id, meal.getDateTime(), meal.getDescription(), meal.getCalories()));
    }

    public Meal read(Meal meal) {
        return meals.get(meal.getId());
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

    public void delete(Meal meal) {
        if (meal.getId() < 0)
            return;
        meals.remove(meal.getId());
    }

    public List<Meal> getMeals() {
        return new ArrayList<>(meals.values());
    }
}
