package ru.javawebinar.topjava.service.jpa;

import org.slf4j.LoggerFactory;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.service.AbstractMealServiceTest;

@ActiveProfiles({Profiles.ACTIVE_DB, Profiles.JPA})
public class JPAMealServiceTest extends AbstractMealServiceTest {
    static {
        LOG = LoggerFactory.getLogger(JPAMealServiceTest.class);
    }
}
