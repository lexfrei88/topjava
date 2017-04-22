package ru.javawebinar.topjava.service;

import org.slf4j.LoggerFactory;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;

@ActiveProfiles({Profiles.ACTIVE_DB, Profiles.JPA})
public class JPAMealServiceTest extends AbstractMealServiceTest {
    static {
        LOG = LoggerFactory.getLogger(JPAMealServiceTest.class);
    }
}
