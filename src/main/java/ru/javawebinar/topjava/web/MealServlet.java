package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.web.meal.MealRestController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

/**
 * User: gkislin
 * Date: 19.08.2014
 */
public class MealServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(MealServlet.class);
    private ConfigurableApplicationContext appCtx;
    private MealRestController controller;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml");
        controller = appCtx.getBean(MealRestController.class);
    }

    @Override
    public void destroy() {
        appCtx.close();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String action = request.getParameter("action");
        System.out.println();

        switch (action == null ? "all" : action) {
            case "delete":
                int id = getId(request);
                LOG.info("Delete {}", id);
                controller.delete(id);
                response.sendRedirect("meals");
                break;
            case "create":
            case "update":
                final Meal meal = action.equals("create") ?
                        new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000) :
                        controller.get(getId(request));
                request.setAttribute("meal", meal);
                request.getRequestDispatcher("/meal.jsp").forward(request, response);
                break;
            case "filter":
                LOG.info("getFilteredByDate");
                LocalDate startDate = parseDate(request.getParameter("startDate"));
                LocalTime startTime = parseTime(request.getParameter("startTime"));
                LocalDate endDate = parseDate(request.getParameter("endDate"));
                LocalTime endTime = parseTime(request.getParameter("endTime"));

                request.setAttribute("meals", controller.getFilteredWithExceeded(startDate, startTime, endDate, endTime));
                request.getRequestDispatcher("/meals.jsp").forward(request, response);
                break;
            case "all":
            default:
                LOG.info("getAll");
                request.setAttribute("meals", controller.getAllWithExceeded());
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
        controller.save(meal);
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
