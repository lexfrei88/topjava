package ru.javawebinar.topjava;

import ru.javawebinar.topjava.matcher.ModelMatcher;
import ru.javawebinar.topjava.util.exception.ErrorInfo;

public class ErrorInfoTestData {
    public static final ModelMatcher<ErrorInfo> ERROR_INFO_MODEL_MATCHER = ModelMatcher.of(ErrorInfo.class);
    public static final String CAUSE_FIELD_EXCEPTION = "FieldException";
    public static final String CAUSE_DATA_INTEGRITY_VIOLATION_EXCEPTION = "DataIntegrityViolationException";

    public static final ErrorInfo MEAL_CREATE_EXPECTED_ERROR = new ErrorInfo("http://localhost/rest/profile/meals/",
            CAUSE_FIELD_EXCEPTION,
            "description may not be empty<br>");

    public static final ErrorInfo MEAL_UPDATE_EXPECTED_ERROR_WITH_ID = new ErrorInfo("http://localhost/rest/profile/meals/100002",
            CAUSE_FIELD_EXCEPTION,
            "description may not be empty<br>");

    public static final ErrorInfo USER_CREATE_EXPECTED_ERROR = new ErrorInfo("http://localhost/rest/admin/users/",
            CAUSE_FIELD_EXCEPTION,
            "password length must be between 5 and 2147483647<br>");

    public static final ErrorInfo USER_UPDATE_EXPECTED_ERROR_WITH_ID = new ErrorInfo("http://localhost/rest/admin/users/100000",
            CAUSE_FIELD_EXCEPTION,
            "name may not be empty<br>");

    public static final ErrorInfo PROFILE_USER_UPDATE_EXPECTED_ERROR_WITH_ID = new ErrorInfo(
            "http://localhost/rest/profile",
            CAUSE_FIELD_EXCEPTION,
            "name may not be empty<br>");

    public static final ErrorInfo USER_WITH_EQUAL_EMAIL_EXPECTED_ERROR = new ErrorInfo(
            "http://localhost/rest/admin/users",
            CAUSE_DATA_INTEGRITY_VIOLATION_EXCEPTION,
            "User with this email already present in application");

    public static final ErrorInfo MEAL_WITH_EQUAL_DATETIME_EXPECTED_ERROR = new ErrorInfo(
            "http://localhost/rest/profile/meals",
            CAUSE_DATA_INTEGRITY_VIOLATION_EXCEPTION,
            "Meal for this date-time has already exist in application");
}
