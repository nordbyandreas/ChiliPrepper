package com.ChiliPrepper.ChiliPrepper;

import java.util.*;
import com.ChiliPrepper.ChiliPrepper.model.*;
import com.ChiliPrepper.ChiliPrepper.service.*;
import com.ChiliPrepper.ChiliPrepper.web.controller.BotMailSender;
import com.ChiliPrepper.ChiliPrepper.web.controller.QuizController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by Andreas on 22.03.2017.
 *
 * This is the class responsible for sending email regarding various results from Courses and Quizes on ChiliPrepper
 *
 * The class is marked with the @Component  annotation to let Spring boot know about it.
 *
 * The Application class is marked with the   @EnableScheduling   annotation which tells Spring to check for components
 * with methods  marked with  the  @Scheduled  annotation, which many methods in this class is.
 *
 * The Scheduled methods execute on given intervals and checks the DB for new and old results, and sends automaticly sends
 * email to users, (if they have enabled bot-contact).
 *
 * This class could be regarded as the "BOT"-part of this project.
 *
 */

@Component
public class ScheduledMailSender {


    @Autowired
    private CourseService courseService;

    @Autowired
    private QuizService quizService;

    @Autowired
    private AnswerService answerService;

    @Autowired
    private QuizController quizController;

    @Autowired
    private CreatorQuizMailService creatorQuizMailService;

    @Autowired
    private UserService userService;


    /**
     * This method sends the total course average % to the course's creator on a given interval
     * if the creator has enabled it.
     *
     * This method is intended to execute around 1 or 2 times per month, but for later development we'd want the course-creator to set the interval.
     */
    @Scheduled(initialDelay=20000, fixedRate = 120000)  //finds 24 hours in milliseconds
    public void sendCourseAverage() {
        Iterable<Course> courses  = courseService.findAll();

        for (Course course : courses) {
            String[] to = {course.getCreator().getEmail()};

            Iterable<Quiz> quizes = quizService.findAllByCourse_id(course.getId());
            double courseAvg = 0;
            int counter = 0;
            boolean enableMail = course.getCreator().isCreatorCourseUpdate();
            for (Quiz quiz : quizes) {
                if(quizController.getAvgScoreForQuiz(quiz.getId()) != null) {
                    courseAvg += quizController.getAvgScoreForQuiz(quiz.getId());
                    counter += 1;
                }
                else{
                        System.out.println("no quiz results");
                }
            }
            Iterable<Answer> answers = answerService.findAllByCourse_Id(course.getId());
            List<Answer> answerCheck = new ArrayList<>();
            answers.forEach(answerCheck::add);

            if((answerCheck.size() > 0) && enableMail){
                courseAvg = courseAvg / counter;
                String bot = generateBotResponse(courseAvg);
                String message = "Yo, the course average is at:  " + courseAvg + "%  right now. \n\n" + bot;
                BotMailSender.sendFromGMail(to, "Course average results update", message);
            }
        }
    }


    /**
     * This method sends the average results of a single Quiz to the creator of a quiz if he has enabled the contact.
     * Also, it is registered that the creator has received a mail regarding this quiz, so he will not receive duplicate mail.
     *
     * This method is intended to execute ca. 1 or 2 times per week, but for later development we'd want the CourseCreator to set the interval.
     *
     */
    @Scheduled(initialDelay=20000, fixedRate = 120000)   //finds 24 hours in milliseconds
    public void sendQuizResults() {
        Iterable<Course> courses  = courseService.findAll();

        for (Course course : courses) {
            String[] to = {course.getCreator().getEmail()};

            Iterable<Quiz> quizes = quizService.findAllByCourse_id(course.getId());
            boolean enableMail = course.getCreator().isCreatorQuizResults();
            for (Quiz quiz : quizes) {

                //checks if creator has received mail of quiz results previously
                if((creatorQuizMailService.findOneByQuiz_Id(quiz.getId()) == null) && (quizController.getAvgScoreForQuiz(quiz.getId()) != null) && enableMail){
                    double quizAverage = quizController.getAvgScoreForQuiz(quiz.getId());
                    String bot = generateBotResponse(quizAverage);
                    String message = "Yo, the quiz average for " + quiz.getQuizName() + " was at:  " + quizAverage + "%  ! \n\n " + bot;
                    BotMailSender.sendFromGMail(to, "Quiz average results", message);
                    CreatorQuizMail creatorQuizMail = new CreatorQuizMail();
                    creatorQuizMail.setQuiz(quiz);
                    creatorQuizMail.setCreator(course.getCreator());
                    creatorQuizMailService.save(creatorQuizMail);
                }
            }
        }
    }


    /**
     * This method sends course-participants mail regarding topics they struggle with
     *
     * All questions with the same topic are used to calculate a score, and if that score drops below the given threshold
     * a mail is sent to that participant with information.
     *
     * The intention is that a course-creator could attach learning-resources to a topic, and this could be attached to this email.
     * (as of know only a symbolic link to www.google.com is included)
     *
     * This method is intended to execute on regular intervals, and for later development we would want the course-participant to
     * set the intervals.
     *
     */
    @Scheduled(initialDelay=20000, fixedRate = 120000)   //finds 24 hours in milliseconds
    public void sendTopicResults() {

        Iterable<User> users = userService.findAll();
        for (User user : users) {
            HashMap<String, int[]> topicMap = new HashMap<>();
            Iterable<Answer> userAnswers  = answerService.findAllByUser_Id(user.getId());
            boolean enableMail = user.isParticipantTopicUpdate();
            for(Answer answer : userAnswers) {
                String currentTopic = answer.getQuestion().getTopic();
                if (topicMap.containsKey(currentTopic)) {
                    int[] stats = topicMap.get(currentTopic);
                    stats[1] += 1;
                } else {
                    topicMap.put(currentTopic, new int[] {0,1});
                }
                if(answer.isCorrect()) {
                    int[] stats = topicMap.get(currentTopic);
                    stats[0] += 1;
                }
            }

            Iterator it = topicMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                String topic = (String) pair.getKey();
                int[] stats = (int[]) pair.getValue();
                double correct = (double)stats[0];
                double total = (double)stats[1];

                if(((correct/total*100) < 20) && (total > 2) && enableMail){
                    System.out.println("\n\n\n\n\n" + topic + "\n\n\n\n\n\n" + (correct/total*100) + "\n\n\n\n\n\n");
                    System.out.println("send Mail to" + user.getUsername() + "!");
                    String message = "Yo,  " + user.getUsername() + "   seems you're struggling with questions with the topic: " + topic  + ".  \n\n Maybe you should ask somebody for help! \n\n Check out this link for some tips: " +
                            "\n www.google.com \n\n  Have a nice day!";
                    String[] to = {user.getEmail()};
                    BotMailSender.sendFromGMail(to, "Smart Topic helper", message);

                }
            }
        }
    }


    /**
     * This method takes a  (double)result and generates a "BOT"-response fitting the result.
     *
     * This method is a simplistic effort to give the automatic mailsender "BOT" some personality.
     *
     *
     * @param result is the (double)score used to evaluate which message to choose.
     * @return The method returns a String message.
     */
    public String generateBotResponse(double result){
        String message = "";

        if(result < 30){
            message = "That's not great..  Step up your game! \n  ";
        }
        else if (result < 70){
            message = "The class is doing OK, but not impressing anyone.  Acceptable, i guess.. \n\n ";
        }
        else if(result < 101 ){
            message = "WooW, this is pretty good!  Keep it up!!";
        }

        return message;
    }

}