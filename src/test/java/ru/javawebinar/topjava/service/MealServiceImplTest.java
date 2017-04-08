package ru.javawebinar.topjava.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.DbPopulator;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER_ID;
/**
 * @author Alex A.
 * @version 1.0
 *          4/8/2017.
 */
@ContextConfiguration({
    "classpath:spring/spring-app.xml",
    "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class MealServiceImplTest {

    @Autowired
    private MealServiceImpl service;

    @Autowired
    private DbPopulator dbPopulator;

    @Before
    public void setUp() throws Exception {
        dbPopulator.execute();
        setUserMealList();
        setAdminMealList();
    }

    @Test
    public void get_correct_meal() throws Exception {
        Meal actual = service.get(MEAL_2_ID, USER_ID);
        MATCHER.assertEquals(userMealList.get(2), actual);
    }

    @Test(expected = NotFoundException.class)
    public void get_not_found_meal() throws Exception {
        service.get(MEAL_2_ID, ADMIN_ID);
    }

    @Test
    public void delete_correct_meal() throws Exception {
        service.delete(MEAL_7_ID, ADMIN_ID);
        adminMealList.remove(1);

        MATCHER.assertCollectionEquals(adminMealList, service.getAll(ADMIN_ID));
    }

    @Test(expected = NotFoundException.class)
    public void delete_not_found_meal() throws Exception {
        service.delete(MEAL_1_ID, ADMIN_ID);
    }

    @Test
    public void getBetweenDateTimes_top_from_adminMeals_list() throws Exception {
        List<Meal> actual = service.getBetweenDateTimes(
                LocalDateTime.parse("2017-01-15T22:00"),
                LocalDateTime.parse("2017-01-16T22:00"),
                ADMIN_ID);
        MATCHER.assertCollectionEquals(actual, Collections.singletonList(adminMealList.get(0)));
    }

    @Test
    public void getBetweenDateTimes_empty_list() throws Exception {
        List<Meal> actual = service.getBetweenDateTimes(
                LocalDateTime.parse("2020-01-15T22:00"),
                LocalDateTime.parse("2030-01-15T22:00"),
                USER_ID);
        MATCHER.assertCollectionEquals(actual, Collections.emptyList());
    }

    @Test
    public void getBetweenDateTimes_all() throws Exception {
        List<Meal> actual = service.getBetweenDateTimes(
                LocalDateTime.parse("2010-01-15T22:00"),
                LocalDateTime.parse("2020-01-15T22:00"),
                USER_ID);
        MATCHER.assertCollectionEquals(actual, userMealList);
    }

    @Test
    public void getAll() throws Exception {
        MATCHER.assertCollectionEquals(userMealList, service.getAll(USER_ID));
    }

    @Test
    public void update_correct() throws Exception {
        Meal updated = new Meal(MEAL_0_ID, LocalDateTime.parse("2100-01-01T00:00"), "Dinner Update", 1);
        service.update(updated, USER_ID);
        userMealList.set(0, updated);

        MATCHER.assertCollectionEquals(userMealList, service.getAll(USER_ID));
    }

    @Test(expected = NotFoundException.class)
    public void update_not_found_meal() throws Exception {
        Meal updated = new Meal(MEAL_3_ID, LocalDateTime.parse("2100-01-01T00:00"), "Dinner Update", 1);
        service.update(updated, ADMIN_ID);
    }

    @Test
    public void save_correct() throws Exception {
        Meal expected =  new Meal(LocalDateTime.parse("1000-01-01T00:00"), "New Meal", 1111);
        Meal actual = service.save(expected, ADMIN_ID);
        adminMealList.add(expected);

        assertNotNull(actual);
        MATCHER.assertEquals(expected, actual);
        MATCHER.assertCollectionEquals(adminMealList, service.getAll(ADMIN_ID));
    }
}