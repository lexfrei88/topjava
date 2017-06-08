package ru.javawebinar.topjava.web.meal;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.ErrorInfo;
import ru.javawebinar.topjava.web.AbstractControllerTest;
import ru.javawebinar.topjava.web.json.JsonUtil;

import java.time.Month;

import static java.time.LocalDateTime.of;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static ru.javawebinar.topjava.ErrorInfoTestData.ERROR_INFO_MODEL_MATCHER;
import static ru.javawebinar.topjava.ErrorInfoTestData.MEAL_WITH_EQUAL_DATETIME_EXPECTED_ERROR;
import static ru.javawebinar.topjava.TestUtil.userHttpBasic;
import static ru.javawebinar.topjava.UserTestData.USER;
import static ru.javawebinar.topjava.web.meal.MealRestController.REST_URL;

public class MealRestControllerTestNotTransactional extends AbstractControllerTest {

    @Test
    public void testCreateWithEqualDateTime() throws Exception {
        Meal create = new Meal(of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500);

        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(create))
                .with(userHttpBasic(USER)));

        ErrorInfo returned = ERROR_INFO_MODEL_MATCHER.fromJsonAction(action);

        ERROR_INFO_MODEL_MATCHER.assertEquals(MEAL_WITH_EQUAL_DATETIME_EXPECTED_ERROR, returned);
    }
}
