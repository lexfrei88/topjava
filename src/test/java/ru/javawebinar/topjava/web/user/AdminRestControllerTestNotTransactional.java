package ru.javawebinar.topjava.web.user;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.util.exception.ErrorInfo;
import ru.javawebinar.topjava.web.AbstractControllerTest;
import ru.javawebinar.topjava.web.json.JsonUtil;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javawebinar.topjava.ErrorInfoTestData.ERROR_INFO_MODEL_MATCHER;
import static ru.javawebinar.topjava.ErrorInfoTestData.USER_WITH_EQUAL_EMAIL_EXPECTED_ERROR;
import static ru.javawebinar.topjava.TestUtil.userHttpBasic;
import static ru.javawebinar.topjava.UserTestData.ADMIN;
import static ru.javawebinar.topjava.web.user.AdminRestController.REST_URL;

public class AdminRestControllerTestNotTransactional extends AbstractControllerTest {
    @Test
    public void testCreateWithEqualEmail() throws Exception {
        User expected = new User(null, "New", "admin@gmail.com", "newPass", 2300, Role.ROLE_USER, Role.ROLE_ADMIN);
        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN))
                .content(JsonUtil.writeValue(expected)))
                .andExpect(status().isConflict());

        ErrorInfo returned = ERROR_INFO_MODEL_MATCHER.fromJsonAction(action);

        ERROR_INFO_MODEL_MATCHER.assertEquals(USER_WITH_EQUAL_EMAIL_EXPECTED_ERROR, returned);
    }
}
