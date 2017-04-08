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

    public static final int MEAL_0_ID = START_SEQ + 2;
    public static final int MEAL_1_ID = START_SEQ + 3;
    public static final int MEAL_2_ID = START_SEQ + 4;
    public static final int MEAL_3_ID = START_SEQ + 5;
    public static final int MEAL_4_ID = START_SEQ + 6;
    public static final int MEAL_5_ID = START_SEQ + 7;
    public static final int MEAL_6_ID = START_SEQ + 8;
    public static final int MEAL_7_ID = START_SEQ + 9;

    public static List<Meal> userMealList;
    public static List<Meal> adminMealList;

    public static void setUserMealList() {
        userMealList = new ArrayList<>(Arrays.asList(
                new Meal(MEAL_0_ID, LocalDateTime.parse("2017-01-16T14:00"), "Dinner 2", 4000),
                new Meal(MEAL_1_ID, LocalDateTime.parse("2017-01-16T09:33"), "Breakfast 2", 1450),
                new Meal(MEAL_2_ID, LocalDateTime.parse("2017-01-15T22:00"), "Supper", 51),
                new Meal(MEAL_3_ID, LocalDateTime.parse("2017-01-15T16:00"), "Dinner", 400),
                new Meal(MEAL_4_ID, LocalDateTime.parse("2017-01-15T12:00"), "Lunch", 1100),
                new Meal(MEAL_5_ID, LocalDateTime.parse("2017-01-15T09:00"), "Breakfast", 450)
        ));
    }

    public static void setAdminMealList() {
        adminMealList = new ArrayList<>(Arrays.asList(
                new Meal(MEAL_6_ID, LocalDateTime.parse("2017-01-15T22:01"), "Admin Supper", 580),
                new Meal(MEAL_7_ID, LocalDateTime.parse("2017-01-15T10:00"), "Admin Breakfast", 1400)
        ));
    }
}
