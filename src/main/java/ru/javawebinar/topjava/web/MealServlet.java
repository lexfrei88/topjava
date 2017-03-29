package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MealStorageInHashMap;
import ru.javawebinar.topjava.dao.MealStorage;
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

    private MealStorage mealStorage = new MealStorageInHashMap();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug("forward to meals.jsp");

        String redirect = "meals.jsp";
        if (req.getParameter("action") != null && !req.getParameter("action").isEmpty()) {
            switch (req.getParameter("action")) {
                case "delete": {
                    mealStorage.delete(Integer.parseInt(req.getParameter("id")));
                    resp.sendRedirect("meals");
                    break;
                }
                case "update": {
                    redirect = "mealedit.jsp";
                    int id = Integer.parseInt(req.getParameter("id"));
                    Meal readMeal = mealStorage.read(id);
                    req.setAttribute("readMeal", readMeal);
                    req.getRequestDispatcher(redirect).forward(req, resp);
                    break;
                }
                case "create": {
                    redirect = "mealedit.jsp";
                    req.getRequestDispatcher(redirect).forward(req, resp);
                    break;
                }
            }
        } else {
            List<MealWithExceed> filteredWithExceeded =
                    MealsUtil.getFilteredWithExceeded(mealStorage.getMeals(), LocalTime.MIN, LocalTime.MAX, 2000);
            req.setAttribute("mealsList", filteredWithExceeded);
            req.getRequestDispatcher(redirect).forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug("post from meals.jsp");

        Meal meal = composeMealFromRequestData(req);

        String redirect = "/topjava";
        if (req.getParameter("action") != null && !req.getParameter("action").isEmpty()) {
            switch (req.getParameter("action")) {
                case "create": {
                    mealStorage.create(meal);
                    break;
                }
                case "update": {
                    mealStorage.update(meal);
                    break;
                }
            }
        }

        resp.sendRedirect(redirect);
    }

    private Meal composeMealFromRequestData(HttpServletRequest req) {
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

        return new Meal(id, localDateTime, description, calories);
    }
}
