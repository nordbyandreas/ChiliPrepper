package com.ChiliPrepper.ChiliPrepper.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;




/**
 * Created by Andreas on 15.02.2017.
 *
 *
 * Configuration class for Template- and view-resolvers
 *
 *
 */
@Configuration                  //marks as config-class
public class TemplateConfig {


    /**
     *
     * Creates a template resolver bean
     *
     * sets classpath and suffix
     *
     * @return
     */
    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        final SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setCacheable(false);
        templateResolver.setPrefix("classpath:/templates/");   //set classpath for templates to the /templates directory
        templateResolver.setSuffix(".html");                   // sets automatic sufix .html so we can write index instead of index.html
        return templateResolver;
    }


    /**
     *
     * Creates a Spring template engine
     *
     * @return
     */
    @Bean
    public SpringTemplateEngine templateEngine() {
        final SpringTemplateEngine springTemplateEngine = new SpringTemplateEngine();
        springTemplateEngine.addTemplateResolver(templateResolver());     //add the template-resolver defined above.
        springTemplateEngine.addDialect(new SpringSecurityDialect());
        return springTemplateEngine;
    }


    /**
     *  Creates Thymeleaf ViewResolver Bean
     *
     *
     * @return
     */
    @Bean
    public ThymeleafViewResolver viewResolver() {
        final ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());                //set template engine to the one defined above
        viewResolver.setOrder(1);
        return viewResolver;
    }
}
