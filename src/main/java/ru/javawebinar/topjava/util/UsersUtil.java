package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.User;

import java.util.Comparator;

/**
 * @author Alex A.
 * @version 1.0
 *          3/31/2017.
 */
public class UsersUtil {

    public static final Comparator<User> comparatorByNameAsc = Comparator.comparing(User::getName);

}
