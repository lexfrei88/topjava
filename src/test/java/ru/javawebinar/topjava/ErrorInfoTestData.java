package ru.javawebinar.topjava;

import ru.javawebinar.topjava.matcher.ModelMatcher;
import ru.javawebinar.topjava.util.exception.ErrorInfo;

public class ErrorInfoTestData {
    public static final ModelMatcher<ErrorInfo> ERROR_INFO_MODEL_MATCHER = ModelMatcher.of(ErrorInfo.class);
    public static final String URL = "http://localhost/rest/profile/meals/";
    public static final String CAUSE = "FieldException";
    public static final String DETAIL = "calories must be between 10 and 5000<br>description may not be empty<br>";

    public static final ErrorInfo EXPECTED_ERROR = new ErrorInfo(URL, CAUSE, DETAIL);

    public static final ErrorInfo EXPECTED_ERROR_WITH_ID = new ErrorInfo(URL + 100002, CAUSE, DETAIL);
}
