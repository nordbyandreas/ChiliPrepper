package com.ChiliPrepper.ChiliPrepper.web.controller;

import com.ChiliPrepper.ChiliPrepper.model.*;
import com.ChiliPrepper.ChiliPrepper.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;

/**
 * Created by Christer on 20.02.2017.
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


    @RequestMapping(path = "/addQuiz", method = RequestMethod.POST)
    public String addQuiz(Model model, @ModelAttribute Quiz quiz, @RequestParam Long courseId) {
        Course course = courseService.findOne(courseId);
        quiz.setCourse(course);
        quiz.setPublished(false);
        quizService.save(quiz);
        return "redirect:/courses" + course.getId();
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

        User user = (User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
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


        Iterable<Answer> userAnswers = answerService.findAllByQuiz_IdAndUser_Id(quizId, user.getId());
        List<Answer> userNumAnswers = new ArrayList<>();
        List<Answer> userNumCorrectAnswers = new ArrayList<>();
        userAnswers.forEach(userNumAnswers::add);
        for (Answer answer : userAnswers) {
            if (answer.isCorrect()) {
                userNumCorrectAnswers.add(answer);
            }
        }
        double userScore = (double) (userNumCorrectAnswers.size() * 100 / userNumAnswers.size());


        Iterable<Answer> totalAnswers = answerService.findAllByQuiz_Id(quizId);
        List<Answer> numAnswers = new ArrayList<>();
        List<Answer> numCorrectAnswers = new ArrayList<>();
        totalAnswers.forEach(numAnswers::add);
        for (Answer answer : totalAnswers) {
            if (answer.isCorrect()) {
                numCorrectAnswers.add(answer);
            }
        }
        double avgScore = (double) (numCorrectAnswers.size() * 100 / numAnswers.size());

        model.addAttribute("userScore", userScore);
        model.addAttribute("avgScore", avgScore);

        //Call method for sending mail
        //sendQuizResultsByMail(user, quizId, courseId);

        return "quizEvent";
    }


    private void sendQuizResultsByMail(User user, Long quizId, Long courseId) {
        Iterable<Answer> allAnswers = answerService.findAllByQuiz_Id(quizId);


        Iterable<Question> questions = questionService.findAllByQuiz_Id(quizId);

        ArrayList<Double> results = getQuizResults(questions);

        //MailSenderTest.sendFromGMail("chiliprepper.bot@gmail.com", userEmail);
    }

    @RequestMapping(path = "/submitAnswer", method = RequestMethod.POST)
    public String submitAnswer(@RequestParam Long questionId, @RequestParam Long courseId, @RequestParam Long quizId, Principal principal, @RequestParam String answer) {

        User user = (User)((UsernamePasswordAuthenticationToken)principal).getPrincipal();
        user = userService.findByUsername(user.getUsername());

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
        }
        else{
            newAnswer.setCorrect(false);
        }

        answerService.save(newAnswer);


        return "redirect:/courses/" + course.getId() + "/" + quiz.getId() + "/quiz";

    }




        //publish quiz
    @RequestMapping("/publishQuiz")
    public String publishQuiz(@RequestParam Long quizId){


        Quiz quiz = quizService.findOne(quizId);
        quiz.setPublished(true);

        Course course = quiz.getCourse();


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

    private ArrayList<Double> getQuizResults(Iterable<Question> questions) {
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
