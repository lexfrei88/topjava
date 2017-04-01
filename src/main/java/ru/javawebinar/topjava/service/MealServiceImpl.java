package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.List;

@Service
public class MealServiceImpl implements MealService {

    @Autowired
    private MealRepository repository;

    @Override
    public Meal save(Meal meal) {
        return repository.save(meal);
    }

    @Override
    public void delete(int mealId, int userId) throws NotFoundException {
        if (!repository.delete(mealId, userId)) {
            throw new NotFoundException("Meal does not belong to user or does not exist.");
        }
    }

    @Override
    public Meal get(int mealId, int id) {
        Meal getMeal = repository.get(mealId, id);
        if (getMeal == null) {
            throw new NotFoundException("Meal does not belong to user or does not exist.");
        }
        return getMeal;
    }

    @Override
    public List<Meal> getAll(int userId) {
        return repository.getAll(userId);
    }
}