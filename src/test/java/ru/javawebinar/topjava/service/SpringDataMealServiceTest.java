package ru.javawebinar.topjava.service;

import org.slf4j.LoggerFactory;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;

@ActiveProfiles({Profiles.ACTIVE_DB, Profiles.SPRING_DATA})
public class SpringDataMealServiceTest extends AbstractMealServiceTest {
    static {
        LOG = LoggerFactory.getLogger(SpringDataMealServiceTest.class);
    }
}
