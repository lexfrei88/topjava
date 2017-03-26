package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MealStorageInHashMap;
import ru.javawebinar.topjava.dao.MealStorageInterface;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collections;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Meal's servlet.
 *
 * @author Alex A.
 * @version 1.0
 *          3/24/2017.
 */
public class MealServlet extends HttpServlet {
    private static final Logger LOG = getLogger(MealServlet.class);
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

    private MealStorageInterface mealStorageInterface = new MealStorageInHashMap();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug("forward to meals.jsp");

        List<MealWithExceed> filteredWithExceeded =
                MealsUtil.getFilteredWithExceeded(mealStorageInterface.getMeals(), LocalTime.MIN, LocalTime.MAX, 2000);

        if (req.getParameter("readId")!= null && !req.getParameter("readId").isEmpty()) {
            try {
                int readId = Integer.parseInt(req.getParameter("readId"));
                for (MealWithExceed mealWithExceed : filteredWithExceeded) {
                    if (mealWithExceed.getId() == readId) {
                        filteredWithExceeded = Collections.singletonList(mealWithExceed);
                        break;
                    }
                }
            }
            catch (NumberFormatException e) {

            }
        }

        req.setAttribute("mealsList", filteredWithExceeded);
        req.getRequestDispatcher("meals.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug("post from meals.jsp");

        int id;
        try {
            id = Integer.parseInt(req.getParameter("id"));
        } catch (NumberFormatException e) {
            id = -1;
        }

        LocalDateTime localDateTime;
        try {
            localDateTime = LocalDateTime.parse(req.getParameter("date"), DATE_TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            localDateTime = null;
        }

        String description = req.getParameter("description");
        if (description == null || description.isEmpty())
            description = null;

        int calories;
        try {
            calories = Integer.parseInt(req.getParameter("calories"));
        } catch (NumberFormatException e) {
            calories = -1;
        }

        Meal meal = new Meal(id, localDateTime, description, calories);

        String redirect = "/topjava";
        if (req.getParameter("button") != null) {
            switch (req.getParameter("button")) {
                case "Create": {
                    mealStorageInterface.create(meal);
                    break;
                }
                case "Read": {
                    Meal readMeal = mealStorageInterface.read(meal);
                    if (readMeal != null)
                        redirect = redirect + "?readId=" + readMeal.getId();
                    break;
                }
                case "Update": {
                    mealStorageInterface.update(meal);
                    break;
                }
                case "Delete": {
                    mealStorageInterface.delete(meal);
                    break;
                }
            }
        }
//        req.getRequestDispatcher("meals.jsp").forward(req, resp);
        resp.sendRedirect(redirect);
    }
}
