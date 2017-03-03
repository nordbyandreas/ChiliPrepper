package com.ChiliPrepper.ChiliPrepper.web.controller;

import com.ChiliPrepper.ChiliPrepper.model.Alternative;
import com.ChiliPrepper.ChiliPrepper.model.Course;
import com.ChiliPrepper.ChiliPrepper.model.Question;
import com.ChiliPrepper.ChiliPrepper.model.Quiz;
import com.ChiliPrepper.ChiliPrepper.service.AlternativeService;
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

    @Autowired
    private AlternativeService alternativeService;


    @RequestMapping(path = "/addQuestion", method = RequestMethod.POST)
    public String addQuestion(Model model, @ModelAttribute Question newQuestion, @RequestParam Long quizId, @RequestParam String alt1, @RequestParam String alt2, @RequestParam String alt3){


        Quiz quiz = quizService.findOne(quizId);
        newQuestion.setQuiz(quiz);

        Course course = quiz.getCourse();

        questionService.save(newQuestion);


        Alternative altOne = new Alternative();
        altOne.setAlternative(alt1);
        altOne.setQuestion(newQuestion);
        alternativeService.save(altOne);

        Alternative altTwo = new Alternative();
        altTwo.setAlternative(alt2);
        altTwo.setQuestion(newQuestion);
        alternativeService.save(altTwo);


        Alternative altThree = new Alternative();
        altThree.setAlternative(alt3);
        altThree.setQuestion(newQuestion);
        alternativeService.save(altThree);

        return "redirect:/courses/" + course.getId() + "/" + quizId;
    }


    @RequestMapping("/courses/{courseId}/{quizId}/{questionId}")
    public String question(Model model, @PathVariable Long questionId){

        Question question = questionService.findOne(questionId);
        model.addAttribute("question", question);

        return "question";
    }

}
