package com.ChiliPrepper.ChiliPrepper.web.controller;

import com.ChiliPrepper.ChiliPrepper.model.*;
import com.ChiliPrepper.ChiliPrepper.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/**
 * Created by Christer on 20.02.2017.
 */
@Controller
public class QuizController {

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
    public String addQuiz(@ModelAttribute Quiz quiz, @RequestParam Long courseId) {
        Course course = courseService.findOne(courseId);
        quiz.setCourse(course);
        quiz.setPublished(false);
        quizService.save(quiz);
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
    public String quizzer(Principal principal, Model model, @PathVariable Long quizId, @PathVariable Long courseId){

        Course course = courseService.findOne(courseId);
        Quiz quiz = quizService.findOne(quizId);


        Iterable<Question> questions = questionService.findAllByQuiz_Id(quizId);

        for (Question question : questions){

            if(answerService.findOneByQuestion_Id(question.getId()) == null){
                Iterable<Alternative> alternatives = alternativeService.findAllByQuestion_Id(question.getId());

                model.addAttribute("quiz", quiz);
                model.addAttribute("alternatives", alternatives);
                model.addAttribute("course", course);
                String correctAnswer = question.getCorrectAnswer();
                model.addAttribute("correctAnswer", correctAnswer);
                return "quizEvent";

            }
        }



        



        return "quizEvent";
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



}
