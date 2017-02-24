package com.ChiliPrepper.ChiliPrepper.web.controller;

import com.ChiliPrepper.ChiliPrepper.model.Course;
import com.ChiliPrepper.ChiliPrepper.model.Question;
import com.ChiliPrepper.ChiliPrepper.model.Quiz;
import com.ChiliPrepper.ChiliPrepper.service.CourseService;
import com.ChiliPrepper.ChiliPrepper.service.QuestionService;
import com.ChiliPrepper.ChiliPrepper.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String quiz(Model model, @PathVariable Long quizId){
        model.addAttribute("quiz", quizService.findOne(quizId));
        model.addAttribute("question", new Question());
        model.addAttribute("questions", questionService.findAll());



        System.out.println(quizService.findOne(quizId));

        return "quiz";
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
