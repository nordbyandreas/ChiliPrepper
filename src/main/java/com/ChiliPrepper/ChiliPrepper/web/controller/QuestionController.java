package com.ChiliPrepper.ChiliPrepper.web.controller;

import com.ChiliPrepper.ChiliPrepper.model.Course;
import com.ChiliPrepper.ChiliPrepper.model.Question;
import com.ChiliPrepper.ChiliPrepper.model.Quiz;
import com.ChiliPrepper.ChiliPrepper.service.QuestionService;
import com.ChiliPrepper.ChiliPrepper.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Andreas on 24.02.2017.
 */
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuizService quizService;


    @RequestMapping(path = "/addQuestion", method = RequestMethod.POST)
    public String addQuestion(@ModelAttribute Question question, @RequestParam Long quizId){

        Quiz quiz = quizService.findOne(quizId);
        question.setQuiz(quiz);
        questionService.save(question);

        Course course = quiz.getCourse();
        System.out.println(question.getQuestion());

        return "redirect:/courses/" + course.getId() + "/" + quiz.getId();
    }





}
