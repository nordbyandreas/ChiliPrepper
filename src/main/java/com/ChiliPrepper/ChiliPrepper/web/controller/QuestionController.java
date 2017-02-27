package com.ChiliPrepper.ChiliPrepper.web.controller;

import com.ChiliPrepper.ChiliPrepper.model.Course;
import com.ChiliPrepper.ChiliPrepper.model.Question;
import com.ChiliPrepper.ChiliPrepper.model.Quiz;
import com.ChiliPrepper.ChiliPrepper.service.QuestionService;
import com.ChiliPrepper.ChiliPrepper.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Andreas on 24.02.2017.
 */
@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuizService quizService;


    @RequestMapping(path = "/addQuestion", method = RequestMethod.POST)
    public String addQuestion(Model model, @ModelAttribute Question newQuestion, @RequestParam Long quizId, @RequestParam String alt1, @RequestParam String alt2, @RequestParam String alt3){


        Quiz quiz = quizService.findOne(quizId);
        newQuestion.setQuiz(quiz);


        Course course = quiz.getCourse();

        System.out.println(alt1);
        System.out.println(alt2);
        System.out.println(alt3);

        System.out.println(newQuestion.getTheQuestion());
        System.out.println(newQuestion.getCorrectAnswer());


        questionService.save(newQuestion);



        return "redirect:/courses/" + course.getId() + "/" + quizId;
    }


    @RequestMapping("/courses/{courseId}/{quizId}/{questionId}")
    public String question(Model model, @PathVariable Long questionId){

        Question question = questionService.findOne(questionId);
        model.addAttribute("question", question);

        return "question";
    }

}
