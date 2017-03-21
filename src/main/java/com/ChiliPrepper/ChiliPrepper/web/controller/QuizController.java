package com.ChiliPrepper.ChiliPrepper.web.controller;

import com.ChiliPrepper.ChiliPrepper.model.*;
import com.ChiliPrepper.ChiliPrepper.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.annotation.WebServlet;
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
    public String quizzer(Model model, @PathVariable Long courseId, @PathVariable Long quizId){

        Course course = courseService.findOne(courseId);
        Quiz quiz = quizService.findOne(quizId);

        Iterable<Question> questions = questionService.findAllByQuiz_Id(quizId);

        for (Question question : questions){

            if(answerService.findOneByQuestion_Id(question.getId()) == null){

                List<Alternative> alts = (List<Alternative>) alternativeService.findAllByQuestion_Id(question.getId());
                List<String> alternatives = new ArrayList<String>();
                for(Alternative alt : alts){
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

        return "quizEvent";
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
        List<Double> results = new ArrayList<Double>();


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
            System.out.println(numCorrectAnswers.size());
            System.out.println(numAnswers.size());
            results.add((double)numCorrectAnswers.size() / numAnswers.size() * 100);
        }


        String quizName = quizService.findOne(quizId).getQuizName();
        model.addAttribute("quizName", quizName);

        model.addAttribute("results", results);




        return "graph:: quizChart";
    }


}
