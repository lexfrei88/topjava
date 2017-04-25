package ru.javawebinar.topjava.service.springdata;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.AbstractUserServiceTest;

import java.util.Arrays;

import static ru.javawebinar.topjava.MealTestData.ADMIN_MEAL1;
import static ru.javawebinar.topjava.MealTestData.ADMIN_MEAL2;
import static ru.javawebinar.topjava.UserTestData.*;

@ActiveProfiles({Profiles.ACTIVE_DB, Profiles.SPRING_DATA})
public class SpringDataUserServiceTest extends AbstractUserServiceTest {
    @Test
    public void testGetUserMeal() throws Exception {
        User user = service.get(ADMIN_ID);
        MATCHER.assertEquals(ADMIN, user);
        ru.javawebinar.topjava.MealTestData.MATCHER.assertCollectionEquals(Arrays.asList(ADMIN_MEAL1, ADMIN_MEAL2), user.getMeals());
    }
}
