package com.ChiliPrepper;

/**
 * Created by Andreas on 15.02.2017.
 *
 *Main method, starts the program
 *
 *
 *@EnableScheduling allows Spring to scan for and execute methods marked with the @Scheduled
 * annotation on set intervals
 * Used for automatic email.
 *
 *
 *@EnableAutoConfiguration Lets Spring scan for classes marked with the @Configuration
 * annotation. These classes configure the system
 * (Security, Database, Templates)
 *
 *
 * @ComponentScan Lets spring scan for components in the package specified.
 * Classes marked with @Component, @Controller, @Service and @Repository are recognized and treated as such.
 *
 *
 */
//TODO

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableAutoConfiguration                                                      //searches for classes marked as @Configuration and uses them for config
@ComponentScan(basePackages = "com.ChiliPrepper.ChiliPrepper")
public class Application {
    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }
}
