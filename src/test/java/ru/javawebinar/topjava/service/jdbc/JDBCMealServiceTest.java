package ru.javawebinar.topjava.service.jdbc;

import org.slf4j.LoggerFactory;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.service.AbstractMealServiceTest;

@ActiveProfiles({Profiles.ACTIVE_DB, Profiles.JDBC})
public class JDBCMealServiceTest extends AbstractMealServiceTest {
    static {
        LOG = LoggerFactory.getLogger(JDBCMealServiceTest.class);
    }
}
