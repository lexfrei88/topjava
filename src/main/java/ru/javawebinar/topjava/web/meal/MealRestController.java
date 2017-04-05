package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealWithExceeded;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@Controller
public class MealRestController {
    private static final Logger LOG = getLogger(MealRestController.class);

    @Autowired
    private MealService service;

    public Meal save(Meal meal) {
        LOG.info("save");
        meal.setUserId(AuthorizedUser.id());
        return service.save(meal, AuthorizedUser.id());
    }

    public void delete(int id) {
        LOG.info("delete " + id);
        service.delete(id, AuthorizedUser.id());
    }

    public Meal get(int mealId) {
        LOG.info("Get " + mealId);
        return service.get(mealId, AuthorizedUser.id());
    }

    public List<MealWithExceeded> getFilteredWithExceeded(LocalDate startDate, LocalTime startTime,
                                                          LocalDate endDate, LocalTime endTime) {
        LOG.info("getFilteredWithExceeded");
        List<MealWithExceeded> filteredWithExceeded;
        if (startTime == null || endTime == null) {
            filteredWithExceeded = MealsUtil.getWithExceeded(
                    service.getFilteredByDate(startDate, endDate, AuthorizedUser.id()),
                    AuthorizedUser.getCaloriesPerDay());
        } else {
            filteredWithExceeded = MealsUtil.getFilteredWithExceeded(
                    service.getFilteredByDate(startDate, endDate, AuthorizedUser.id()),startTime,
                    endTime, AuthorizedUser.getCaloriesPerDay());
        }
        return filteredWithExceeded;
    }
}