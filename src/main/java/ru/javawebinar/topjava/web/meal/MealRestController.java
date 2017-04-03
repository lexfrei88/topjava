package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealWithExceeded;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.slf4j.LoggerFactory.getLogger;

@Controller
public class MealRestController {
    private static final Logger LOG = getLogger(MealRestController.class);

    @Autowired
    private MealService service;

    public Meal save(Meal meal) {
        LOG.info("save");
        meal.setUserId(AuthorizedUser.id());
        return service.save(meal);
    }

    public void delete(int id) {
        LOG.info("delete " + id);
        service.delete(id, AuthorizedUser.id());
    }

    public Meal get(int mealId) {
        LOG.info("Get " + mealId);
        return service.get(mealId, AuthorizedUser.id());
    }

    public List<Meal> getAll() {
        LOG.info("getAll");
        return service.getAll(AuthorizedUser.id());
    }

    public List<Meal> getFilteredByDate(LocalDate startDate, LocalDate endDate) {
        LOG.info("getFilteredByDate");
        List<Meal> filteredList;
        if ((startDate == null || endDate == null)) {
            filteredList = getAll();
        } else {
            filteredList = getAll().stream()
                    .filter(meal -> DateTimeUtil.isBetween(meal.getDate(), startDate, endDate))
                    .collect(Collectors.toList());
        }
        return filteredList;
    }

    public List<MealWithExceeded> getFilteredWithExceeded(LocalDate startDate, LocalTime startTime,
                                                          LocalDate endDate, LocalTime endTime) {
        LOG.info("getFilteredWithExceeded");
        List<MealWithExceeded> filteredWithExceeded;
        if (startTime == null || endTime == null) {
            filteredWithExceeded = MealsUtil.getWithExceeded(getFilteredByDate(startDate, endDate), AuthorizedUser.getCaloriesPerDay());
        } else {
            filteredWithExceeded = MealsUtil.getFilteredWithExceeded(getFilteredByDate(startDate, endDate),startTime, endTime, AuthorizedUser.getCaloriesPerDay());
        }
        return filteredWithExceeded;
    }

    public List<MealWithExceeded> getAllWithExceeded() {
        return MealsUtil.getWithExceeded(getAll(), AuthorizedUser.getCaloriesPerDay());
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String action = request.getParameter("action");
        System.out.println();

        switch (action == null ? "all" : action) {
            case "delete":
                int id = getId(request);
                LOG.info("Delete {}", id);
                delete(id);
                response.sendRedirect("meals");
                break;
            case "create":
            case "update":
                final Meal meal = action.equals("create") ?
                        new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000) :
                        get(getId(request));
                request.setAttribute("meal", meal);
                request.getRequestDispatcher("/meal.jsp").forward(request, response);
                break;
            case "filter":
                LOG.info("getFilteredByDate");
                LocalDate startDate = parseDate(request.getParameter("startDate"));
                LocalTime startTime = parseTime(request.getParameter("startTime"));
                LocalDate endDate = parseDate(request.getParameter("endDate"));
                LocalTime endTime = parseTime(request.getParameter("endTime"));

                request.setAttribute("meals", getFilteredWithExceeded(startDate, startTime, endDate, endTime));
                request.getRequestDispatcher("/meals.jsp").forward(request, response);
                break;
            case "all":
            default:
                LOG.info("getAll");
                request.setAttribute("meals", getAllWithExceeded());
                request.getRequestDispatcher("/meals.jsp").forward(request, response);
                break;
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");

        Meal meal = new Meal(id.isEmpty() ? null : Integer.valueOf(id),
                LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("description"),
                Integer.valueOf(request.getParameter("calories")));

        LOG.info(meal.isNew() ? "Create {}" : "Update {}", meal);
        save(meal);
        response.sendRedirect("meals");
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.valueOf(paramId);
    }

    private LocalDate parseDate(String temporal) {
        return temporal.isEmpty() ? null : LocalDate.parse(temporal);
    }

    private LocalTime parseTime(String temporal) {
        return temporal.isEmpty() ? null : LocalTime.parse(temporal);
    }
}