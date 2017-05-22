package ru.javawebinar.topjava.web.meal;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealWithExceed;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Alex on 5/22/2017.
 */
@Controller
@RequestMapping("/ajax/meals")
public class MealAjaxController extends AbstractMealController {
    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MealWithExceed> getAll() {
        return super.getAll();
    }

    @PostMapping
    public void create(@RequestParam("dateTime") LocalDateTime dateTime,
                       @RequestParam("description") String description,
                       @RequestParam("calories") int calories) {

        Meal meal = new Meal(null, dateTime, description, calories);
        super.create(meal);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }
}
