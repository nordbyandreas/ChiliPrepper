package com.ChiliPrepper.ChiliPrepper;

/**
 * Created by Andreas on 22.03.2017.
 */

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ChiliPrepper.ChiliPrepper.model.*;
import com.ChiliPrepper.ChiliPrepper.service.*;
import com.ChiliPrepper.ChiliPrepper.web.controller.BotMailSender;
import com.ChiliPrepper.ChiliPrepper.web.controller.MailController;
import com.ChiliPrepper.ChiliPrepper.web.controller.QuizController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class ScheduledMailSender {


    @Autowired
    private CourseService courseService;

    @Autowired
    private QuizService quizService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private AnswerService answerService;

    @Autowired
    private QuizMailService quizMailService;

    @Autowired
    private QuizController quizController;

    @Autowired
    private CreatorQuizMailService creatorQuizMailService;


    private static final Logger log = LoggerFactory.getLogger(ScheduledMailSender.class);
    private MailController mailController = new MailController();
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");


    @Scheduled(fixedRate = 3000000)  //finn 1 mnd i millisekunder
    public void sendCourseAverage() {
        Iterable<Course> courses  = courseService.findAll();

        //TODO extract into helper method.  Create check for user-bot-preferences

        for (Course course : courses) {
            String[] to = {course.getCreator().getEmail()};

            Iterable<Quiz> quizes = quizService.findAllByCourse_id(course.getId());
            double courseAvg = 0;
            int counter = 0;

            for (Quiz quiz : quizes) {
                if(quizController.getAvgScore(quiz.getId()) != null) {
                    courseAvg += quizController.getAvgScore(quiz.getId());
                    System.out.println("\n\n\n\n" + courseAvg + "\n\n\n\n");
                    counter += 1;
                    System.out.println("\n\n\n\n" + counter + "\n\n\n\n");

                }
                else{
                        System.out.println("no quiz results");
                }
            }
            Iterable<Answer> answers = answerService.findAllByCourse_Id(course.getId());
            List<Answer> answerCheck = new ArrayList<>();
            answers.forEach(answerCheck::add);

            System.out.println(answerCheck.size());

            if(answerCheck.size() > 0){
                courseAvg = courseAvg / counter;
                String message = "Yo, the course average is at:  " + courseAvg + "  right now";
                BotMailSender.sendFromGMail(to, "Course average results update", message);
            }


        }
    }


    @Scheduled(fixedRate = 3000000)   //finn døgn i millisekunder
    public void sendQuizResults() {
        Iterable<Course> courses  = courseService.findAll();

        //TODO extract into helper method.  Create check for user-bot-preferences

        for (Course course : courses) {
            String[] to = {course.getCreator().getEmail()};

            Iterable<Quiz> quizes = quizService.findAllByCourse_id(course.getId());

            for (Quiz quiz : quizes) {
                if((creatorQuizMailService.findOneByQuiz_Id(quiz.getId()) == null) && (quizController.getAvgScore(quiz.getId()) != null)){
                    double quizAverage = quizController.getAvgScore(quiz.getId());
                    String message = "Yo, the quiz average for " + quiz.getQuizName() + " was at:  " + quizAverage + "  !";
                    BotMailSender.sendFromGMail(to, "Quiz average results", message);
                    CreatorQuizMail creatorQuizMail = new CreatorQuizMail();
                    creatorQuizMail.setQuiz(quiz);
                    creatorQuizMail.setCreator(course.getCreator());
                    creatorQuizMailService.save(creatorQuizMail);
                }
                //log.info("Mail sent.");
                //mailController.sendMail();
            }
        }
    }

    @Scheduled(fixedRate = 20000)   //finn døgn i millisekunder
    public void sendTopicResults() {
        //log.info("Mail sent.");
        //mailController.sendMail();
    }


}
