package com.ChiliPrepper.ChiliPrepper.web.controller;

import com.ChiliPrepper.ChiliPrepper.model.Course;
import com.ChiliPrepper.ChiliPrepper.model.Quiz;
import com.ChiliPrepper.ChiliPrepper.service.CourseService;
import com.ChiliPrepper.ChiliPrepper.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Christer on 20.02.2017.
 */
@Controller
public class QuizController {
    @Autowired
    private QuizService quizService;

    @Autowired
    private CourseService courseService;

    @RequestMapping(path = "/addQuiz", method = RequestMethod.POST)
    public String addQuiz(@ModelAttribute Quiz quiz, @RequestParam Long courseId) {
        Course course = courseService.findOne(courseId);
        quiz.setCourse(course);
        quizService.save(quiz);
        return "redirect:/courses/" + course.getId();
    }
}
