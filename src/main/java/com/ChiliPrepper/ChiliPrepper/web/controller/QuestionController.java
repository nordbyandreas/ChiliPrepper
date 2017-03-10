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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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






    //method for routing to editQuestion
    @RequestMapping("/courses/{courseId}/{quizId}/{questionId}/editQuestion")
    public String editQuestion(Model model, @PathVariable Long questionId){
        Question question = questionService.findOne(questionId);
        Iterable<Alternative> alts = alternativeService.findAllByQuestion_Id(questionId);
        model.addAttribute("question", question);
        model.addAttribute("quizId", question.getQuiz().getId());
        model.addAttribute("courseId", question.getQuiz().getCourse().getId());
        List<Alternative> alternatives = new ArrayList<>();
        alts.forEach(alternatives :: add);

        for(int i = 0; i < alternatives.size(); i++){
            model.addAttribute("alt" + Integer.toString(i + 1), alternatives.get(i));
        }


        System.out.println("\n\n\n\n   \n\n\n\n\n");


        return "editQuestion";
    }


    
    //method for saving editedQuestion
    @RequestMapping("/courses/{courseId}/{quizId}/{questionId}/editQuestion/saveEditQuestion")
    public String saveEditQuestion(@RequestParam String alt1, @RequestParam String alt2, @RequestParam String alt3,
                                   @RequestParam Long alt1Id, @RequestParam Long alt2Id, @RequestParam Long alt3Id,
                                   @RequestParam Long questionId, @RequestParam String correctAnswer, @RequestParam String theQuestion,
                                   @RequestParam Long quizId, @RequestParam String topic){

        Alternative a1 = alternativeService.findOne(alt1Id);
        Alternative a2 = alternativeService.findOne(alt2Id);
        Alternative a3 = alternativeService.findOne(alt3Id);
        a1.setAlternative(alt1);
        a2.setAlternative(alt2);
        a3.setAlternative(alt3);
        alternativeService.save(a1);
        alternativeService.save(a2);
        alternativeService.save(a3);

        Question question = questionService.findOne(questionId);
        question.setCorrectAnswer(correctAnswer);
        question.setTheQuestion(theQuestion);
        question.setTopic(topic);
        questionService.save(question);


        Quiz quiz = quizService.findOne(quizId);

        return "redirect:/courses/" + quiz.getCourse().getId() + "/" + quiz.getId() + "/" + question.getId() + "/editQuestion?questionId=" + question.getId();
    }


    //method for deleting question
    @RequestMapping("/deleteQuestion")
    public String deleteQuestion(@RequestParam Long questionId){
        Question question = questionService.findOne(questionId);
        questionService.delete(question);
        Long courseId = question.getQuiz().getCourse().getId();
        Long quizId = question.getQuiz().getId();

        return "redirect:/courses/" + courseId + "/" + quizId + "/editQuiz?quizId=" + quizId;
    }


}
