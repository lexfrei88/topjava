package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalTime;

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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug("forward to meals.jsp");

        req.setAttribute("mealsList", MealsUtil.getFilteredWithExceeded(MealsUtil.getMeals(), LocalTime.MIN, LocalTime.MAX, 2000));
        req.getRequestDispatcher("meals.jsp").forward(req, resp);
    }
}
