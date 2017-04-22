package ru.javawebinar.topjava;

import org.springframework.context.support.GenericXmlApplicationContext;

public class SpringMain {
    public static void main(String[] args) {
//        ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml");
        GenericXmlApplicationContext appCtx = new GenericXmlApplicationContext();

        appCtx.getEnvironment().setActiveProfiles(Profiles.ACTIVE_DB, Profiles.REPOSITORY_IMPLEMENTATION);
        appCtx.load("spring/spring-tools.xml", "spring/spring-db.xml", "spring/spring-app.xml");
        appCtx.refresh();

        System.out.println("Bean definition names: ");
        for (String s : appCtx.getBeanDefinitionNames()) {
            System.out.println(s);
        }

        appCtx.close();
    }
}
