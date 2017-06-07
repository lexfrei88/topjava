package ru.javawebinar.topjava;

import ru.javawebinar.topjava.matcher.ModelMatcher;
import ru.javawebinar.topjava.util.exception.ErrorInfo;

public class ErrorInfoTestData {
    public static final ModelMatcher<ErrorInfo> ERROR_INFO_MODEL_MATCHER = ModelMatcher.of(ErrorInfo.class);
    public static final String CAUSE_1 = "FieldException";
    public static final String CAUSE_2 = "DataIntegrityViolationException";

    public static final String MEAL_URL = "http://localhost/rest/profile/meals/";

    public static final String USER_URL = "http://localhost/rest/admin/users";
    public static final String PROFILE_USER_URL = "http://localhost/rest/profile";

    public static final String MEAL_DETAIL = "description may not be empty<br>";

    public static final String USER_DETAIL_1 = "password length must be between 5 and 2147483647<br>";
    public static final String USER_DETAIL_2 = "name may not be empty<br>";
    public static final String USER_DETAIL_3 = "User with this email already present in application";

    public static final ErrorInfo MEAL_CREATE_EXPECTED_ERROR = new ErrorInfo(MEAL_URL, CAUSE_1, MEAL_DETAIL);

    public static final ErrorInfo MEAL_UPDATE_EXPECTED_ERROR_WITH_ID = new ErrorInfo(MEAL_URL + 100002, CAUSE_1, MEAL_DETAIL);

    public static final ErrorInfo USER_CREATE_EXPECTED_ERROR = new ErrorInfo(USER_URL + '/', CAUSE_1, USER_DETAIL_1);

    public static final ErrorInfo USER_UPDATE_EXPECTED_ERROR_WITH_ID = new ErrorInfo(USER_URL + '/' + 100000, CAUSE_1, USER_DETAIL_2);

    public static final ErrorInfo PROFILE_USER_UPDATE_EXPECTED_ERROR_WITH_ID = new ErrorInfo(PROFILE_USER_URL, CAUSE_1, USER_DETAIL_2);

    public static final ErrorInfo USER_WITH_EQUAL_EMAIL_EXPECTED_ERROR = new ErrorInfo(USER_URL, CAUSE_2, USER_DETAIL_3);

}
