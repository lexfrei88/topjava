package ru.javawebinar.topjava;

/**
 * User: gkislin
 * Date: 19.08.2014
 */
public class Profiles {
    public static final String
            POSTGRES_DB = "postgres",
            HSQL_DB = "hsqldb";

    public static final String ACTIVE_DB = HSQL_DB;

    public static final String
            JDBC_HSQLDB = "jdbc-hsqldb",
            JDBC_POSGRESQL = "jdbc-postgres",
            JPA = "jpa",
            SPRING_DATA = "datajpa";

    public static final String JDBC = JDBC_HSQLDB;

    public static final String REPOSITORY_IMPLEMENTATION = SPRING_DATA;
}
