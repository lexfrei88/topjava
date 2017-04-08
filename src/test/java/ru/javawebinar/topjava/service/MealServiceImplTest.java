package ru.javawebinar.topjava.service;

import org.junit.After;
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

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void get_correct_meal() throws Exception {
        Meal actual = service.get(MEAL_2_ID, USER_ID);
        MATCHER.assertEquals(userMealList.get(4), actual);
    }

    @Test(expected = NotFoundException.class)
    public void get_strangers_meal() throws Exception {
        Meal actual = service.get(MEAL_2_ID, ADMIN_ID);
        fail(actual.toString());
    }

    @Test
    public void delete_correct_meal() throws Exception {
        service.delete(MEAL_3_ID, ADMIN_ID);
        adminMealList.remove(1);

        MATCHER.assertCollectionEquals(adminMealList, service.getAll(ADMIN_ID));
    }

    @Test(expected = NotFoundException.class)
    public void delete_stranger_meal() throws Exception {
        service.delete(MEAL_1_ID, ADMIN_ID);
        fail();
    }

    @Test
    public void getBetweenDateTimes() throws Exception {

    }

    @Test
    public void getAll() throws Exception {
        MATCHER.assertCollectionEquals(userMealList, service.getAll(USER_ID));
    }

    @Test
    public void update_correct() throws Exception {
        Meal updated = new Meal(MEAL_5_ID, LocalDateTime.parse("2111-01-22T22:22"), "Dinner Update", 3333);
        service.update(updated, USER_ID);
        userMealList.set(0, updated);

        MATCHER.assertCollectionEquals(userMealList, service.getAll(USER_ID));
    }

    @Test(expected = NotFoundException.class)
    public void update_strangers_meal() throws Exception {
        Meal updated = service.get(MEAL_3_ID, USER_ID);
        service.update(updated, ADMIN_ID);
        fail();
    }

    @Test
    public void save_correct() throws Exception {
        Meal expected =  new Meal(LocalDateTime.parse("1000-01-31T00:01"), "New Meal", 1111);
        Meal actual = service.save(expected, ADMIN_ID);
        adminMealList.add(expected);

        assertNotNull(actual);
        MATCHER.assertEquals(expected, actual);
        MATCHER.assertCollectionEquals(adminMealList, service.getAll(ADMIN_ID));
    }
}