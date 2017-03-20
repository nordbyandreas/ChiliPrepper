package com.ChiliPrepper;

/**
 * Created by Andreas on 15.02.2017.
 */
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
