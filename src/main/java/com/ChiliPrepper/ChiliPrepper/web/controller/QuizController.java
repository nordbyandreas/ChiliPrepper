package com.ChiliPrepper.ChiliPrepper.web.controller;

import com.ChiliPrepper.ChiliPrepper.model.*;
import com.ChiliPrepper.ChiliPrepper.service.*;
import com.ChiliPrepper.ChiliPrepper.web.FlashMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.*;

/**
 * Created by Christer on 20.02.2017.
 *
 * The @Controller annotation lets Spring know that this is a controller.
 *
 * Controller classes handles URI requests from the browser with methods marked with the
 * @RequestMapping annotation.
 *
 * These methods return a String with the name of which HTML file to render from the
 * templates directory. Various objects or variables may be added to, or read from, the model.
 * (adding something to the model is like adding something to that particular HTML file rendering).
 *
 */
@Controller
public class QuizController {

    @Autowired
    private UserService userService;

    @Autowired
    private QuizService quizService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private AnswerService answerService;

    @Autowired
    private AlternativeService alternativeService;

    @Autowired
    private QuizMailService quizMailService;


    @RequestMapping(path = "/addQuiz", method = RequestMethod.POST)
    public String addQuiz(@ModelAttribute Quiz quiz, @RequestParam Long courseId, RedirectAttributes redirectAttributes) {

        Course course = courseService.findOne(courseId);

        if(quiz.getQuizName().isEmpty()){
            redirectAttributes.addFlashAttribute("flash",new FlashMessage("Could not create quiz. Quiz name cannot be empty! ", FlashMessage.Status.FAILURE));
            return "redirect:/courses/" + course.getId();
        }
        quiz.setCourse(course);
        quiz.setPublished(false);
        quizService.save(quiz);
        redirectAttributes.addFlashAttribute("flash",new FlashMessage("Quiz created ! ", FlashMessage.Status.SUCCESS));
        return "redirect:/courses/" + course.getId();
    }



    //single quiz page
    @RequestMapping("/courses/{courseId}/{quizId}")
    public String quiz(Model model, @PathVariable Long quizId, @PathVariable Long courseId){

        Course course = courseService.findOne(courseId);


        Quiz quiz = quizService.findOne(quizId);
        model.addAttribute("quiz", quiz);


        model.addAttribute("newQuestion", new Question());


        Iterable<Question> questions = questionService.findAllByQuiz_Id(quizId);
        model.addAttribute("questions", questions);

        model.addAttribute("course", course);

        return "quiz";
    }




    //method for serving questions to a quiz-taker
    @RequestMapping("/courses/{courseId}/{quizId}/quiz")
    public String quizzer(Principal principal, Model model, @PathVariable Long quizId, @PathVariable Long courseId) {

        String username = principal.getName();
        User user = userService.findByUsername(username);
        Course course = courseService.findOne(courseId);
        Quiz quiz = quizService.findOne(quizId);

        Iterable<Question> questions = questionService.findAllByQuiz_Id(quizId);

        for (Question question : questions) {

            if (answerService.findOneByQuestion_IdAndUser_Id(question.getId(), user.getId()) == null) {

                List<Alternative> alts = (List<Alternative>) alternativeService.findAllByQuestion_Id(question.getId());
                List<String> alternatives = new ArrayList<String>();
                for (Alternative alt : alts) {
                    alternatives.add(alt.getAlternative());
                }
                alternatives.add(question.getCorrectAnswer());

                Collections.shuffle(alternatives);

                model.addAttribute("quiz", quiz);
                model.addAttribute("alternatives", alternatives);
                model.addAttribute("course", course);
                model.addAttribute("question", question);
                model.addAttribute("questionId", question.getId());
                model.addAttribute("quizId", quizId);
                model.addAttribute("courseId", courseId);

                return "quizEvent";

            }
        }


        String message = "Congratz, you've completed this quiz!";
        model.addAttribute("message", message);
        model.addAttribute("quizId", quizId);
        model.addAttribute("courseId", courseId);


        double userScore = getUserScore(quizId, user);


        double avgScore = getAvgScore(quizId);

        model.addAttribute("userScore", userScore);
        model.addAttribute("avgScore", avgScore);


        sendQuizResultsByMail(user, quizId);

        return "quizEvent";
    }

    public Double getAvgScore(Long quizId)  {
        Iterable<Answer> totalAnswers = answerService.findAllByQuiz_Id(quizId);
        List<Answer> numAnswers = new ArrayList<>();
        List<Answer> numCorrectAnswers = new ArrayList<>();
        totalAnswers.forEach(numAnswers::add);
        for (Answer answer : totalAnswers) {
            if (answer.isCorrect()) {
                numCorrectAnswers.add(answer);
            }
        }
        try{
            return (double) (numCorrectAnswers.size() * 100 / numAnswers.size());
        }
        catch(ArithmeticException ae){
            System.out.println(ae.getMessage());
            return null;
        }
    }

    public Double getUserScore(Long quizId, User user) {
        Iterable<Answer> userAnswers = answerService.findAllByQuiz_IdAndUser_Id(quizId, user.getId());
        List<Answer> userNumAnswers = new ArrayList<>();
        List<Answer> userNumCorrectAnswers = new ArrayList<>();
        userAnswers.forEach(userNumAnswers::add);
        for (Answer answer : userAnswers) {
            if (answer.isCorrect()) {
                userNumCorrectAnswers.add(answer);
            }
        }
        try{
            return (double) (userNumCorrectAnswers.size() * 100 / userNumAnswers.size());
        }
        catch(ArithmeticException ae){
            System.out.println(ae.getMessage());
            return null;
        }


    }

    private void sendQuizResultsByMail(User user, Long quizId) {
        boolean enableMail = user.isParticipantQuizResults();
        if((quizMailService.findOneByQuiz_IdAndParticipant_Id(quizId, user.getId()) == null) && enableMail){
            String[] to = {user.getEmail()};
            BotMailSender.sendFromGMail(to, generateMailSubject(quizId), generateMailBody(quizId, user.getId()));
            QuizMail quizMail = new QuizMail();
            quizMail.setQuiz(quizService.findOne(quizId));
            quizMail.setParticipant(user);
            quizMailService.save(quizMail);
        }


    }

    public String generateMailSubject(Long quizId){
        Quiz q = quizService.findOne(quizId);

        return q.getQuizName() + " - results";
    }

    public String generateMailBody(Long quizId, Long userId){
        User user = userService.findOne(userId);
        Quiz quiz = quizService.findOne(quizId);

        String username = user.getUsername();
        double userScore = getUserScore(quizId, user);

        String message = "";

        if(userScore < 30){
            message = "Oh.. Not great =p  I'm guessing you didn't prepare for this quiz =/ \n  You should put in some more work.";
        }
        else if (userScore < 70){
            message = "Not bad, but don't get cocky!  Keep it up :) ";
        }
        else if(userScore < 101 ){
            message = "Excellent work!  You're doing great.  Keep it up!!";
        }


        String body = "Hi " + username + "!\n\n" +
                "You got " + userScore + "% correct on the " + quiz.getQuizName() + " quiz.\n\n" +
                message + "\n\n" + "ChiliPrepper";



        return body;
    }


    @RequestMapping(path = "/submitAnswer", method = RequestMethod.POST)
    public String submitAnswer(RedirectAttributes redirectAttributes, @RequestParam Long questionId, @RequestParam Long courseId, @RequestParam Long quizId, Principal principal, @RequestParam String answer) {

        String username = principal.getName();
        User user = userService.findByUsername(username);

        Course course = courseService.findOne(courseId);
        Quiz quiz = quizService.findOne(quizId);
        Question question = questionService.findOne(questionId);

        Answer newAnswer = new Answer();
        newAnswer.setQuestion(question);
        newAnswer.setQuiz(quiz);
        newAnswer.setCourse(course);
        newAnswer.setUser(user);

        newAnswer.setAnswer(answer);

        if(answer.equals(question.getCorrectAnswer())){
            newAnswer.setCorrect(true);
            redirectAttributes.addFlashAttribute("flash",new FlashMessage("Correct! Good job!", FlashMessage.Status.SUCCESS));

        }
        else{
            newAnswer.setCorrect(false);
            redirectAttributes.addFlashAttribute("flash",new FlashMessage("Wrong answer, too bad! ", FlashMessage.Status.FAILURE));

        }
        answerService.save(newAnswer);
        return "redirect:/courses/" + course.getId() + "/" + quiz.getId() + "/quiz";
    }




    //publish quiz
    @RequestMapping("/publishQuiz")
    public String publishQuiz(@RequestParam Long quizId, RedirectAttributes redirectAttributes){


        Quiz quiz = quizService.findOne(quizId);
        Course course = quiz.getCourse();

        if(quiz.isPublished()){
            quiz.setPublished(false);
            redirectAttributes.addFlashAttribute("flash",new FlashMessage("Quiz Unpublished! ", FlashMessage.Status.SUCCESS));
        }
        else{
            quiz.setPublished(true);
            redirectAttributes.addFlashAttribute("flash",new FlashMessage("Quiz published! ", FlashMessage.Status.SUCCESS));

        }

        quizService.save(quiz);
        return "redirect:/courses/" + course.getId();
    }


    @RequestMapping(value = "/quizChart/{quizId}", method = RequestMethod.GET)
    public String quizChart(Model model, @PathVariable Long quizId) {

        Iterable<Question> questions = questionService.findAllByQuiz_Id(quizId);

        ArrayList<Double> results = getQuizResults(questions);


        String quizName = quizService.findOne(quizId).getQuizName();
        model.addAttribute("quizName", quizName);

        model.addAttribute("results", results);




        return "graph:: quizChart";
    }

    public ArrayList<Double> getQuizResults(Iterable<Question> questions) {
        ArrayList<Double> results = new ArrayList<>();
        for (Question question : questions){
            Iterable<Answer> ans = answerService.findAllByQuestion_Id(question.getId());
            List<Answer> numAnswers = new ArrayList<>();
            List<Answer> numCorrectAnswers = new ArrayList<>();
            ans.forEach(numAnswers :: add);
            for (Answer answer : ans){
                if(answer.isCorrect()){
                    numCorrectAnswers.add(answer);
                }
            }

            results.add((double)numCorrectAnswers.size() * 100 / numAnswers.size());
        }
        return results;
    }

    //for returning the editQuiz page
    @RequestMapping("/courses/{courseId}/{quizId}/editQuiz")
    public String editQuiz(Model model, @RequestParam Long quizId){
        Quiz quiz = quizService.findOne(quizId);
        Iterable<Question> questions = questionService.findAllByQuiz_Id(quizId);
        model.addAttribute("quiz", quiz);
        model.addAttribute("questions", questions);
        model.addAttribute("course", quiz.getCourse());
        return "editQuiz";
    }


    @RequestMapping(path = "/saveEditQuiz", method = RequestMethod.POST)
    public String saveEditQuiz(@ModelAttribute Quiz quiz) {
        Course course = courseService.findOne(quiz.getCourse().getId());
        System.out.println("\n\n\n\n Fant course \n\n\n\n");
        quizService.save(quiz);
        System.out.println("\n\n\n\n saved quiz? \n\n\n\n");

        return "redirect:/courses/" + course.getId() + "/" + quiz.getId();
    }



    //method for deleting quiz
    @RequestMapping("/deleteQuiz")
    public String deleteQuiz(@RequestParam Long quizId){
        Quiz quiz = quizService.findOne(quizId);


        quizService.delete(quiz);
        return "redirect:/courses/" + quiz.getCourse().getId();
    }



}