package ru.javawebinar.topjava;

import ru.javawebinar.topjava.matcher.ModelMatcher;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static ru.javawebinar.topjava.model.BaseEntity.START_SEQ;

public class MealTestData {

    public static final ModelMatcher<Meal> MATCHER = new ModelMatcher<>();

    public static final int USER_MEAL_0_ID = START_SEQ + 2;
    public static final int USER_MEAL_1_ID = START_SEQ + 3;
    public static final int USER_MEAL_2_ID = START_SEQ + 4;
    public static final int USER_MEAL_3_ID = START_SEQ + 5;
    public static final int USER_MEAL_4_ID = START_SEQ + 6;
    public static final int USER_MEAL_5_ID = START_SEQ + 7;

    public static final int ADMIN_MEAL_0_ID = START_SEQ + 8;
    public static final int ADMIN_MEAL_1_ID = START_SEQ + 9;

    public static final Meal USER_MEAL_0 = new Meal(USER_MEAL_0_ID, LocalDateTime.parse("2017-01-16T14:00"), "Dinner 2", 4000);
    public static final Meal USER_MEAL_1 = new Meal(USER_MEAL_1_ID, LocalDateTime.parse("2017-01-16T09:33"), "Breakfast 2", 1450);
    public static final Meal USER_MEAL_2 = new Meal(USER_MEAL_2_ID, LocalDateTime.parse("2017-01-15T22:00"), "Supper", 51);
    public static final Meal USER_MEAL_3 = new Meal(USER_MEAL_3_ID, LocalDateTime.parse("2017-01-15T16:00"), "Dinner", 400);
    public static final Meal USER_MEAL_4 = new Meal(USER_MEAL_4_ID, LocalDateTime.parse("2017-01-15T12:00"), "Lunch", 1100);
    public static final Meal USER_MEAL_5 = new Meal(USER_MEAL_5_ID, LocalDateTime.parse("2017-01-15T09:00"), "Breakfast", 450);

    public static final Meal ADMIN_MEAL_0 = new Meal(ADMIN_MEAL_0_ID, LocalDateTime.parse("2017-01-15T22:01"), "Admin Supper", 580);
    public static final Meal ADMIN_MEAL_1 = new Meal(ADMIN_MEAL_1_ID, LocalDateTime.parse("2017-01-15T10:00"), "Admin Breakfast", 1400);
}
