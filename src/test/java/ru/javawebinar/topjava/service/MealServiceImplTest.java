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
import java.util.Arrays;
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
    }

    @Test
    public void testGet() throws Exception {
        Meal actual = service.get(USER_MEAL_2_ID, USER_ID);
        MATCHER.assertEquals(USER_MEAL_2, actual);
    }

    @Test(expected = NotFoundException.class)
    public void testGetNotFound() throws Exception {
        service.get(USER_MEAL_0_ID, ADMIN_ID);
    }

    @Test
    public void testDelete() throws Exception {
        service.delete(ADMIN_MEAL_1_ID, ADMIN_ID);

        MATCHER.assertCollectionEquals(Collections.singletonList(ADMIN_MEAL_0), service.getAll(ADMIN_ID));
    }

    @Test(expected = NotFoundException.class)
    public void testDeleteNotFound() throws Exception {
        service.delete(USER_MEAL_1_ID, ADMIN_ID);
    }

    @Test
    public void testGetBetweenDateTimes() throws Exception {
        List<Meal> actual = service.getBetweenDateTimes(
                LocalDateTime.parse("2017-01-15T22:00"),
                LocalDateTime.parse("2017-01-16T22:00"),
                ADMIN_ID);
        MATCHER.assertCollectionEquals(actual, Collections.singletonList(ADMIN_MEAL_0));
    }

    @Test
    public void testGetAll() throws Exception {
        MATCHER.assertCollectionEquals(Arrays.asList(USER_MEAL_0,
                USER_MEAL_1,
                USER_MEAL_2,
                USER_MEAL_3,
                USER_MEAL_4,
                USER_MEAL_5
                ), service.getAll(USER_ID));
    }

    @Test
    public void testUpdate() throws Exception {
        Meal updated = new Meal(USER_MEAL_0_ID, LocalDateTime.parse("2100-01-01T00:00"), "Dinner Update", 1);
        service.update(updated, USER_ID);

        MATCHER.assertCollectionEquals(Arrays.asList(updated,
                USER_MEAL_1,
                USER_MEAL_2,
                USER_MEAL_3,
                USER_MEAL_4,
                USER_MEAL_5
                ), service.getAll(USER_ID));
    }

    @Test(expected = NotFoundException.class)
    public void testUpdateNotFound() throws Exception {
        Meal updated = new Meal(USER_MEAL_3_ID, LocalDateTime.parse("2100-01-01T00:00"), "Dinner Update", 1);
        service.update(updated, ADMIN_ID);
    }

    @Test
    public void testSave() throws Exception {
        Meal saved =  new Meal(LocalDateTime.parse("1000-01-01T00:00"), "New Meal", 1);
        Meal actual = service.save(saved, ADMIN_ID);

        assertNotNull(actual);
        MATCHER.assertEquals(saved, actual);
        MATCHER.assertCollectionEquals(Arrays.asList(ADMIN_MEAL_0,
                ADMIN_MEAL_1,
                saved
                ), service.getAll(ADMIN_ID));
    }
}