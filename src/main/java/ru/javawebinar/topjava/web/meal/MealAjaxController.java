package ru.javawebinar.topjava.web.meal;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * Created by Alex on 5/22/2017.
 */
@RestController
@RequestMapping("/ajax/profile/meals")
public class MealAjaxController extends AbstractMealController {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MealWithExceed> getAll() {
        return super.getAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @PostMapping
    public void create(@RequestParam("id") Integer id,
                       @RequestParam("dateTime") LocalDateTime dateTime,
                       @RequestParam("description") String description,
                       @RequestParam("calories") int calories) {

        Meal meal = new Meal(dateTime, description, calories);
        super.create(meal);
    }

    @GetMapping("/filter")
    public List<MealWithExceed> getBetween(@RequestParam(value = "startDate", required = false) LocalDate startDate,
                                           @RequestParam(value = "startTime", required = false) LocalTime startTime,
                                           @RequestParam(value = "endDate", required = false) LocalDate endDate,
                                           @RequestParam(value = "endTime", required = false) LocalTime endTime) {

        return super.getBetween(startDate, startTime, endDate, endTime);
    }
}
