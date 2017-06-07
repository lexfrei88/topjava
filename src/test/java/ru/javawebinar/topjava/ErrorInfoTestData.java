package ru.javawebinar.topjava;

import ru.javawebinar.topjava.matcher.ModelMatcher;
import ru.javawebinar.topjava.util.exception.ErrorInfo;

public class ErrorInfoTestData {
    public static final ModelMatcher<ErrorInfo> ERROR_INFO_MODEL_MATCHER = ModelMatcher.of(ErrorInfo.class);

    public static final ErrorInfo EXPECTED_ERROR = new ErrorInfo(
            "http://localhost/rest/profile/meals/",
            "FieldException",
            "calories must be between 10 and 5000<br>description may not be empty<br>");

    public static final ErrorInfo EXPECTED_ERROR_WITH_ID = new ErrorInfo(
            "http://localhost/rest/profile/meals/100002",
            "FieldException",
            "calories must be between 10 and 5000<br>description may not be empty<br>");
}
