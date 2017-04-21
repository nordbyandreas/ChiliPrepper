package com.ChiliPrepper.ChiliPrepper.web.controller;

import java.util.List;
import java.util.ArrayList;
import org.springframework.ui.Model;
import com.ChiliPrepper.ChiliPrepper.model.Quiz;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import com.ChiliPrepper.ChiliPrepper.model.Course;
import com.ChiliPrepper.ChiliPrepper.model.Question;
import com.ChiliPrepper.ChiliPrepper.web.FlashMessage;
import com.ChiliPrepper.ChiliPrepper.model.Alternative;
import com.ChiliPrepper.ChiliPrepper.service.QuizService;
import com.ChiliPrepper.ChiliPrepper.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import com.ChiliPrepper.ChiliPrepper.service.AlternativeService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
     *Renders the page for a single question, where you can make changes
     *
     *
     * @param model
     * @param questionId
     * @return Returns a String which points to the correct HTML file to be rendered
     */
    @RequestMapping("/courses/{courseId}/{quizId}/{questionId}")
    public String renderQuestionView(Model model, @PathVariable Long questionId){

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
    public String addQuestion(@ModelAttribute Question question, @RequestParam Long quizId, @RequestParam String alt1,
                              @RequestParam String alt2, @RequestParam String alt3, RedirectAttributes redirectAttributes){

        Quiz quiz = quizService.findOne(quizId);
        Course course = quiz.getCourse();
        Long courseId = course.getId();

        if(question.getTheQuestion().isEmpty() || question.getCorrectAnswer().isEmpty()){
            String message = "Failed to add question. Needs at least a question and correct answer!";
            redirectAttributes.addFlashAttribute("flash", new FlashMessage(message, FlashMessage.Status.FAILURE));

            return "redirect:/courses/" + courseId + "/" + quizId;
        }

        question.setQuiz(quiz);
        questionService.save(question);

        Alternative altOne = new Alternative();
        altOne.setAlternative(alt1);
        altOne.setQuestion(question);
        alternativeService.save(altOne);

        Alternative altTwo = new Alternative();
        altTwo.setAlternative(alt2);
        altTwo.setQuestion(question);
        alternativeService.save(altTwo);

        Alternative altThree = new Alternative();
        altThree.setAlternative(alt3);
        altThree.setQuestion(question);
        alternativeService.save(altThree);

        String message = "Question added!";
        redirectAttributes.addFlashAttribute("flash",new FlashMessage(message, FlashMessage.Status.SUCCESS));

        return "redirect:/courses/" + courseId + "/" + quizId;
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
    public String saveEditedQuestion(@RequestParam String alt1, @RequestParam String alt2, @RequestParam String alt3,
                                   @RequestParam Long alt1Id, @RequestParam Long alt2Id, @RequestParam Long alt3Id,
                                   @RequestParam Long questionId, @RequestParam String correctAnswer, @RequestParam String theQuestion,
                                   @RequestParam Long quizId, @RequestParam String topic, RedirectAttributes redirectAttributes){

        Alternative altOne = alternativeService.findOne(alt1Id);
        Alternative altTwo = alternativeService.findOne(alt2Id);
        Alternative altThree = alternativeService.findOne(alt3Id);
        altOne.setAlternative(alt1);
        altTwo.setAlternative(alt2);
        altThree.setAlternative(alt3);
        alternativeService.save(altOne);
        alternativeService.save(altTwo);
        alternativeService.save(altThree);

        Question question = questionService.findOne(questionId);
        question.setCorrectAnswer(correctAnswer);
        question.setTheQuestion(theQuestion);
        question.setTopic(topic);
        questionService.save(question);

        String message = "Question updated!";
        redirectAttributes.addFlashAttribute("flash",new FlashMessage(message, FlashMessage.Status.SUCCESS));

        Long courseId = quizService.findOne(quizId).getCourse().getId();
        return "redirect:/courses/" + courseId + "/" + quizId + "/" + questionId;
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

        String message = "Question deleted!";
        redirectAttributes.addFlashAttribute("flash",new FlashMessage(message, FlashMessage.Status.SUCCESS));

        Long courseId = question.getQuiz().getCourse().getId();
        Long quizId = question.getQuiz().getId();
        return "redirect:/courses/" + courseId + "/" + quizId;
    }
}