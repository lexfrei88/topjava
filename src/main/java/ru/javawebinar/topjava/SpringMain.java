package ru.javawebinar.topjava;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.service.UserService;
import ru.javawebinar.topjava.web.meal.MealRestController;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;

public class SpringMain {
    public static void main(String[] args) {
        ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml");
        System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));

//        UserRepository userRepository = (UserRepository) appCtx.getBean("mockUserRepository");
        UserRepository userRepository = appCtx.getBean(UserRepository.class);
        userRepository.getAll();

        UserService userService = appCtx.getBean(UserService.class);
        userService.save(new User(null, "userName", "email", "password", Role.ROLE_ADMIN));

        System.out.println();
        MealRestController controller = appCtx.getBean(MealRestController.class);
        System.out.println(controller.getAll());
        System.out.println();
        controller.delete(6);
        System.out.println(controller.getAll());
        System.out.println();
        controller.save(new Meal(LocalDateTime.now(), "SUPPER", 9999));
        System.out.println(controller.getAll());
        System.out.println();
//        System.out.println(controller.getFiltered(LocalDateTime.of(2015, Month.MAY, 31, 8, 0), LocalDateTime.of(2015, Month.MAY, 31, 14, 0)));
        appCtx.close();
    }
}
