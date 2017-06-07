package ru.javawebinar.topjava.web.meal;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.web.AbstractControllerTest;

import static ru.javawebinar.topjava.MealTestData.getCreated;

public class MealRestControllerTestNotTransactional extends AbstractControllerTest {

    @Autowired
    private MealService service;

    @Test(expected = DataIntegrityViolationException.class)
    public void testCreateWithEqualDateTime() throws Exception {
        service.save(getCreated(), 100000);
        service.save(getCreated(), 100000);
    }
}
