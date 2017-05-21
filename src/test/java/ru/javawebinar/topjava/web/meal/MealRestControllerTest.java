package ru.javawebinar.topjava.web.meal;

import org.junit.Test;
import ru.javawebinar.topjava.web.AbstractControllerTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.javawebinar.topjava.MealTestData.*;

/**
 * Created by Alex on 5/21/2017.
 */
public class MealRestControllerTest extends AbstractControllerTest {

    private static final String MEAL_URL = MealRestController.MEAL_URL + "/";

    @Test
    public void getMeal() throws Exception {
        mockMvc.perform(get(MEAL_URL + MEAL1_ID))
                .andExpect(status().isOk())
                .andExpect(MATCHER.contentMatcher(MEAL1))
                .andDo(print());
    }

    @Test
    public void delete() throws Exception {
        mockMvc.perform(get(MEAL_URL + MEAL1_ID))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void getAll() throws Exception {

    }

    @Test
    public void create() throws Exception {

    }

    @Test
    public void update() throws Exception {

    }

    @Test
    public void getBetween() throws Exception {

    }

}