package com.ChiliPrepper.ChiliPrepper.web.controller;

import com.ChiliPrepper.ChiliPrepper.model.Alternative;
import com.ChiliPrepper.ChiliPrepper.model.Course;
import com.ChiliPrepper.ChiliPrepper.model.Question;
import com.ChiliPrepper.ChiliPrepper.model.Quiz;
import com.ChiliPrepper.ChiliPrepper.service.AlternativeService;
import com.ChiliPrepper.ChiliPrepper.service.QuestionService;
import com.ChiliPrepper.ChiliPrepper.service.QuizService;
import com.ChiliPrepper.ChiliPrepper.web.FlashMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by Andreas on 24.02.2017.
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
 * This controller handles Question objects and views
 *
 */
@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuizService quizService;

    @Autowired
    private AlternativeService alternativeService;





    /**
     *
     * Saves a new question to the database
     *
     *
     * @param newQuestion
     * @param quizId
     * @param alt1
     * @param alt2
     * @param alt3
     * @param redirectAttributes
     * @return Returns a String which points to the correct HTML file to be rendered
     */
    @RequestMapping(path = "/addQuestion", method = RequestMethod.POST)
    public String addQuestion(@ModelAttribute Question newQuestion, @RequestParam Long quizId, @RequestParam String alt1, @RequestParam String alt2, @RequestParam String alt3, RedirectAttributes redirectAttributes){


        Quiz quiz = quizService.findOne(quizId);
        newQuestion.setQuiz(quiz);

        Course course = quiz.getCourse();

        if(newQuestion.getTheQuestion().isEmpty() || newQuestion.getCorrectAnswer().isEmpty()){
            redirectAttributes.addFlashAttribute("flash",new FlashMessage("Could not add question. Needs at least a question and correct answer!", FlashMessage.Status.FAILURE));

            return "redirect:/courses/" + course.getId() + "/" + quizId;
        }

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

        redirectAttributes.addFlashAttribute("flash",new FlashMessage("Question added! ", FlashMessage.Status.SUCCESS));
        return "redirect:/courses/" + course.getId() + "/" + quizId;
    }



    /**
     *
     *Renders the page for a single question, where you can make changes
     *
     *
     * @param model
     * @param questionId
     * @return Returns a String which points to the correct HTML file to be rendered
     */
    @RequestMapping("/courses/{courseId}/{quizId}/{questionId}")
    public String question(Model model, @PathVariable Long questionId){

        Question question = questionService.findOne(questionId);
        model.addAttribute("question", question);
        model.addAttribute("quizId", question.getQuiz().getId());
        model.addAttribute("courseId", question.getQuiz().getCourse().getId());
        Iterable<Alternative> alts = alternativeService.findAllByQuestion_Id(questionId);
        List<Alternative> alternatives = new ArrayList<>();
        alts.forEach(alternatives :: add);
        for(int i = 0; i < alternatives.size(); i++){
            model.addAttribute("alt" + Integer.toString(i + 1), alternatives.get(i));
        }

        return "question";
    }





    /**
     *
     * Saves changes made to a question
     *
     *
     *
     * @param alt1
     * @param alt2
     * @param alt3
     * @param alt1Id
     * @param alt2Id
     * @param alt3Id
     * @param questionId
     * @param correctAnswer
     * @param theQuestion
     * @param quizId
     * @param topic
     * @param redirectAttributes
     * @return Returns a String which points to the correct HTML file to be rendered
     */
    @RequestMapping("/courses/{courseId}/{quizId}/{questionId}/editQuestion/saveEditQuestion")
    public String saveEditQuestion(@RequestParam String alt1, @RequestParam String alt2, @RequestParam String alt3,
                                   @RequestParam Long alt1Id, @RequestParam Long alt2Id, @RequestParam Long alt3Id,
                                   @RequestParam Long questionId, @RequestParam String correctAnswer, @RequestParam String theQuestion,
                                   @RequestParam Long quizId, @RequestParam String topic, RedirectAttributes redirectAttributes){

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

        redirectAttributes.addFlashAttribute("flash",new FlashMessage("Question updated ! ", FlashMessage.Status.SUCCESS));

        return "redirect:/courses/" + quiz.getCourse().getId() + "/" + quiz.getId() + "/" + question.getId();
    }




    /**
     *
     * Deletes the question with the given question ID from the database
     *
     * Also, deletes the alternatives and answers with matching question ID
     *
     *
     * @param questionId
     * @param redirectAttributes
     * @return Returns a String which points to the correct HTML file to be rendered
     */
    @RequestMapping("/deleteQuestion")
    public String deleteQuestion(@RequestParam Long questionId, RedirectAttributes redirectAttributes){
        Question question = questionService.findOne(questionId);
        questionService.delete(question);
        Long courseId =question.getQuiz().getCourse().getId();
        Long quizId = question.getQuiz().getId();

        redirectAttributes.addFlashAttribute("flash",new FlashMessage("Question deleted! ", FlashMessage.Status.SUCCESS));


        return "redirect:/courses/" + courseId + "/" + quizId;
    }


}