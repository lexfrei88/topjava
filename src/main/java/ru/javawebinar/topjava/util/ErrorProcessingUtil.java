package ru.javawebinar.topjava.util;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public class ErrorProcessingUtil {

    public static ResponseEntity<String> getResponse(MessageSource messageSource, BindingResult result, String prefix) {
        StringBuilder sb = new StringBuilder();
        result.getFieldErrors().forEach(fieldError -> sb.append(messageSource.getMessage(prefix + fieldError.getField(), null, LocaleContextHolder.getLocale()))
                                                        .append(" ")
                                                        .append(messageSource.getMessage(fieldError, LocaleContextHolder.getLocale()))
                                                        .append("<br>"));
        return new ResponseEntity<>(sb.toString(), HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
