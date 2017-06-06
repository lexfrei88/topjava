package ru.javawebinar.topjava.util.exception;

import org.springframework.dao.DataIntegrityViolationException;

public class EqualEmailsException extends DataIntegrityViolationException {

    public EqualEmailsException(String msg) {
        super(msg);
    }
}
