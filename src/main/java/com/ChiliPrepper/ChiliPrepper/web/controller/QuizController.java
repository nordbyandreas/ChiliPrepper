package com.ChiliPrepper.ChiliPrepper.web.controller;

import java.util.*;
import java.security.Principal;
import org.springframework.ui.Model;
import com.ChiliPrepper.ChiliPrepper.model.*;
import com.ChiliPrepper.ChiliPrepper.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.ChiliPrepper.ChiliPrepper.web.FlashMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by Christer on 20.02.2017.
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
 * This controller handles Quiz objects and Views
 *
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

    @Autowired
    private QuizMailService quizMailService;

    /**
     *
     *Renders the page for a single quiz
     *
     *
     * @param model
     * @param quizId
     * @param courseId
     * @return Returns a String which points to the correct HTML file to be rendered
     */
    @RequestMapping("/courses/{courseId}/{quizId}")
    public String renderQuizView(Model model, @PathVariable Long quizId, @PathVariable Long courseId){

        Course course = courseService.findOne(courseId);
        Quiz quiz = quizService.findOne(quizId);
        Iterable<Question> questions = questionService.findAllByQuiz_Id(quizId);

        model.addAttribute("quiz", quiz);
        model.addAttribute("newQuestion", new Question());
        model.addAttribute("questions", questions);
        model.addAttribute("course", course);

        return "quiz";

    }







    /**
     *
     *Saves a new quiz to the database
     *
     *
     * @param quiz
     * @param courseId
     * @param redirectAttributes
     * @return Returns a String which points to the correct HTML file to be rendered
     */
    @RequestMapping(path = "/addQuiz", method = RequestMethod.POST)
    public String addQuiz(@ModelAttribute Quiz quiz, @RequestParam Long courseId, RedirectAttributes redirectAttributes) {

        Course course = courseService.findOne(courseId);

        if(quiz.getQuizName().isEmpty()){
            String message = "Could not create quiz. Quiz name cannot be empty!";
            redirectAttributes.addFlashAttribute("flash",new FlashMessage(message, FlashMessage.Status.FAILURE));
            return "redirect:/courses/" + course.getId();
        }

        quiz.setCourse(course);
        quiz.setPublished(false);
        quizService.save(quiz);

        String message = "Quiz created!";
        redirectAttributes.addFlashAttribute("flash",new FlashMessage(message, FlashMessage.Status.SUCCESS));

        return "redirect:/courses/" + courseId;
    }


    /**
     *
     *Renders the quizchart view for a chart of quiz results
     *
     *
     * @param model
     * @param quizId
     * @param courseId
     * @return Returns a String which points to the correct HTML file to be rendered
     */
    @RequestMapping("/courses/{courseId}/{quizId}/chart")
    public String renderQuizChartView(Model model, @PathVariable Long quizId, @PathVariable Long courseId){

        Course course = courseService.findOne(courseId);
        Quiz quiz = quizService.findOne(quizId);

        model.addAttribute("quiz", quiz);
        model.addAttribute("course", course);

        return "quizChart";
    }


    /**
     *
     *Serves and renders the Quiz in progress for a course-participant
     *
     *
     *
     *
     * @param principal
     * @param model
     * @param quizId
     * @param courseId
     * @return Returns a String which points to the correct HTML file to be rendered
     */
    @RequestMapping("/courses/{courseId}/{quizId}/quiz")
    public String quizEventHandler(Principal principal, Model model, @PathVariable Long quizId, @PathVariable Long courseId) {

        String username = principal.getName();
        User user = userService.findByUsername(username);
        Course course = courseService.findOne(courseId);
        Quiz quiz = quizService.findOne(quizId);
        Iterable<Question> questions = questionService.findAllByQuiz_Id(quizId);


        //serves remaining questions to the user:

        for (Question question : questions) {

            boolean questionNotAnswered = (answerService.findOneByQuestion_IdAndUser_Id(question.getId(), user.getId()) == null);
            if (questionNotAnswered) {

                List<Alternative> alts = (List<Alternative>) alternativeService.findAllByQuestion_Id(question.getId());
                List<String> alternatives = new ArrayList<String>();

                for (Alternative alt : alts) {
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

        //when quiz is completed by user:

        double userScore = getUserScoreInQuiz(quizId, user);
        double avgScore = getAvgScoreForQuiz(quizId);

        String message = "Congrats, you've completed this quiz!";
        model.addAttribute("message", message);
        model.addAttribute("quizId", quizId);
        model.addAttribute("courseId", courseId);
        model.addAttribute("userScore", userScore);
        model.addAttribute("avgScore", avgScore);

        sendQuizResultsByMail(user, quizId);

        return "quizEvent";
    }



    /**
     *Gets the average percentage score for a quiz (all users)
     *
     *
     *
     * @param quizId
     * @return (double)score or null
     */
    public Double getAvgScoreForQuiz(Long quizId)  {

        List<Answer> numAnswers = new ArrayList<>();

        Iterable<Answer> totalAnswers = answerService.findAllByQuiz_Id(quizId);
        totalAnswers.forEach(numAnswers::add);

        ArrayList<Answer> numCorrectAnswers = getCorrectAnswers(totalAnswers);
        try{
            double percentageScore = (numCorrectAnswers.size() * 100 / numAnswers.size());
            return percentageScore;
        }
        catch(ArithmeticException ae){
            System.out.println(ae.getMessage());
            return null;
        }
    }


    /**
     *
     *Gets a users percentage score in a quiz
     *
     *
     *
     * @param quizId
     * @param user
     * @return (double)score or null
     */
    public Double getUserScoreInQuiz(Long quizId, User user) {

        List<Answer> userNumAnswers = new ArrayList<>();
        Iterable<Answer> userAnswers = answerService.findAllByQuiz_IdAndUser_Id(quizId, user.getId());

        userAnswers.forEach(userNumAnswers::add);
        ArrayList<Answer> userNumCorrectAnswers = getCorrectAnswers(userAnswers);
        try{
            double userPercentageScore = (userNumCorrectAnswers.size() * 100 / userNumAnswers.size());
            return userPercentageScore;
        }
        catch(ArithmeticException ae){
            System.out.println(ae.getMessage());
            return null;
        }
    }


    /**
     *
     *Returns all correct answers from all answers
     *
     *
     * @param userAnswers
     * @return ArrayList containing correct answers
     */
    private ArrayList<Answer> getCorrectAnswers(Iterable<Answer> userAnswers) {
        ArrayList<Answer> correctAnswers = new ArrayList<>();
        for (Answer answer : userAnswers) {
            if (answer.isCorrect()) {
                correctAnswers.add(answer);
            }
        }
        return correctAnswers;
    }


    /**
     *
     *Sends email to users concerning their result on  a quiz
     *
     *
     * @param user
     * @param quizId
     */
    private void sendQuizResultsByMail(User user, Long quizId) {

        boolean mailEnabled = user.isParticipantQuizResults();
        boolean mailNotReceived = (quizMailService.findOneByQuiz_IdAndParticipant_Id(quizId, user.getId()) == null);

        if(mailNotReceived && mailEnabled){
            String[] to = {user.getEmail()};
            BotMailSender.sendFromGMail(to, generateMailSubject(quizId), generateMailBody(quizId, user.getId()));
            QuizMail quizMail = new QuizMail();
            quizMail.setQuiz(quizService.findOne(quizId));
            quizMail.setParticipant(user);
            quizMailService.save(quizMail);
        }
    }


    /**
     *
     *Generates the subject to be used in email
     *
     *
     * @param quizId
     * @return String, "subject"
     */
    public String generateMailSubject(Long quizId){

        Quiz q = quizService.findOne(quizId);
        return q.getQuizName() + " - results";
    }


    /**
     *
     *Generates the entire text to be sendt in email to users
     *
     *
     * @param quizId
     * @param userId
     * @return String, "body of text"
     */
    public String generateMailBody(Long quizId, Long userId){
        User user = userService.findOne(userId);
        Quiz quiz = quizService.findOne(quizId);

        String username = user.getUsername();
        double userScore = getUserScoreInQuiz(quizId, user);

        String message = generateBotFeedback(userScore);

        String body = "Hi " + username + "!\n\n" +
                "You got " + userScore + "% correct on the " + quiz.getQuizName() + " quiz.\n\n" +
                message + "\n\n" + "ChiliPrepper";
        
      return body;
    }


    /**
     *
     *Generates a message depending on the given userScore
     *
     * Used for simple bot personality
     *
     *
     * @param userScore
     * @return String "message"
     */
    private String generateBotFeedback(double userScore) {
        String message = "";
        if(userScore < 30){
            message = "Oh.. Not great =p  I'm guessing you didn't prepare for this quiz =/ \n  You should put in some more work.";
        }
        else if (userScore < 70){
            message = "Not bad, but don't get cocky!  Keep it up :) ";
        }
        else if(userScore < 101 ){
            message = "Excellent work!  You're doing great.  Keep it up!!";
        }
        return message;
    }


    /**
     *
     *Saves the users answer to the Database
     *
     *
     *
     * @param redirectAttributes
     * @param questionId
     * @param courseId
     * @param quizId
     * @param principal
     * @param answer
     * @return Returns a String which points to the correct HTML file to be rendered
     */
    @RequestMapping(path = "/submitAnswer", method = RequestMethod.POST)
    public String submitAnswer(RedirectAttributes redirectAttributes, @RequestParam Long questionId, @RequestParam Long courseId,
                               @RequestParam Long quizId, Principal principal, @RequestParam String answer) {

        String username = principal.getName();
        User user = userService.findByUsername(username);
        Course course = courseService.findOne(courseId);
        Quiz quiz = quizService.findOne(quizId);
        Question question = questionService.findOne(questionId);

        Answer newAnswer = createNewAnswer(answer, user, course, quiz, question);

        boolean isCorrectAnswer = newAnswer.getAnswer().equals(question.getCorrectAnswer());
        if(isCorrectAnswer){
            newAnswer.setCorrect(true);
            String message = "Correct! Good job!";
            redirectAttributes.addFlashAttribute("flash",new FlashMessage(message, FlashMessage.Status.SUCCESS));
        }
        else{
            newAnswer.setCorrect(false);
            String message = "Wrong answer, too bad!";
            redirectAttributes.addFlashAttribute("flash",new FlashMessage(message, FlashMessage.Status.FAILURE));
        }
        answerService.save(newAnswer);

        return "redirect:/courses/" + course.getId() + "/" + quiz.getId() + "/quiz";
    }


  
  
    /**
     *Creates a new answer with the given parameters
     *
     *
     *
     * @param answer
     * @param user
     * @param course
     * @param quiz
     * @param question
     * @return Answer object
     */
    private Answer createNewAnswer(@RequestParam String answer, User user, Course course, Quiz quiz, Question question) {
        Answer newAnswer = new Answer();
        newAnswer.setQuestion(question);
        newAnswer.setQuiz(quiz);
        newAnswer.setCourse(course);
        newAnswer.setUser(user);
        newAnswer.setAnswer(answer);
        return newAnswer;
    }


    /**
     *
     *Publishes a quiz  (becomes available for users participating in that course)
     *
     *Redirects to the same page
     *
     * @param quizId
     * @param redirectAttributes
     * @return Returns a String which points to the correct HTML file to be rendered
     */
    @RequestMapping("/publishQuiz")
    public String publishQuiz(@RequestParam Long quizId, RedirectAttributes redirectAttributes){

        Quiz quiz = quizService.findOne(quizId);
        Course course = quiz.getCourse();

        if(quiz.isPublished()){
            quiz.setPublished(false);

            String message = "Quiz Unpublished!";
            redirectAttributes.addFlashAttribute("flash",new FlashMessage(message, FlashMessage.Status.SUCCESS));
        }
        else{
            quiz.setPublished(true);
            String message = "Quiz Unpublished!";
            redirectAttributes.addFlashAttribute("flash",new FlashMessage(message, FlashMessage.Status.SUCCESS));
        }
        quizService.save(quiz);

        return "redirect:/courses/" + course.getId();
    }



    /**
     *
     *Feeds the html file containg the Javascript for creating a chart with data
     *
     *(Jquery code in the quizChart.html file calls this method every 2000ms to create a "live" chart)
     *
     * @param model
     * @param quizId
     * @return Returns a String which points to the correct HTML file to be rendered (in this case just part of a html file)
     */
    @RequestMapping(value = "/quizChart/{quizId}", method = RequestMethod.GET)
    public String feedQuizChartData(Model model, @PathVariable Long quizId) {

        Iterable<Question> questions = questionService.findAllByQuiz_Id(quizId);
        ArrayList<Double> results = getQuizResults(questions);
        String quizName = quizService.findOne(quizId).getQuizName();

        model.addAttribute("quizName", quizName);
        model.addAttribute("results", results);

        return "graph:: quizChart";
    }

  
  
  
    /**
     *
     *Calculates the average percentage score per question in a quiz and returns it
     *
     *
     * @param questions
     * @return ArrayList containing the average score for each question
     */
    public ArrayList<Double> getQuizResults(Iterable<Question> questions) {

        ArrayList<Double> results = new ArrayList<>();
        for (Question question : questions){
            Iterable<Answer> ans = answerService.findAllByQuestion_Id(question.getId());
            List<Answer> numAnswers = new ArrayList<>();
            ans.forEach(numAnswers :: add);
            ArrayList<Answer> numCorrectAnswers = getCorrectAnswers(ans);
            double percentCorrectAnswers = numCorrectAnswers.size() * 100 / numAnswers.size();
            results.add(percentCorrectAnswers);

        }
        return results;
    }



    /**
     *
     *Saves changes to a quiz
     *
     * Redirects to that quiz page
     *
     *
     * @param quiz
     * @return Returns a String which points to the correct HTML file to be rendered
     */
    @RequestMapping(path = "/saveEditQuiz", method = RequestMethod.POST)
    public String saveEditQuiz(@ModelAttribute Quiz quiz) {

        Course course = courseService.findOne(quiz.getCourse().getId());
        quizService.save(quiz);

        return "redirect:/courses/" + course.getId() + "/" + quiz.getId();
    }



  
    /**
     * Deletes the quiz with the given quizID
     *
     * Redirects to same page
     *
     *
     *
     * @param quizId
     * @return Returns a String which points to the correct HTML file to be rendered
     */
    @RequestMapping("/deleteQuiz")
    public String deleteQuiz(@RequestParam Long quizId, RedirectAttributes redirectAttributes){

        Quiz quiz = quizService.findOne(quizId);
        quizService.delete(quiz);

        String message = "Quiz deleted!";
        redirectAttributes.addFlashAttribute("flash",new FlashMessage(message, FlashMessage.Status.SUCCESS));

        return "redirect:/courses/" + quiz.getCourse().getId();
    }


    /**
     * Renders the editQuiz page
     *
     *
     * @param model
     * @param courseId
     * @param quizId
     * @return  Returns a String which points to the correct HTML file to be rendered
     */
    @RequestMapping(value = "/courses/{courseId}/{quizId}/editName", method = RequestMethod.GET)
    public String renderEditQuiz(Model model, @PathVariable Long courseId, @PathVariable Long quizId) {

        Quiz quiz = quizService.findOne(quizId);
        Course course = courseService.findOne(courseId);

        model.addAttribute("quiz", quiz);
        model.addAttribute("course", course);

        return "editQuiz";

    }


    /**
     *
     * Saves the new quizName to the db
     *
     * Redirects to quiz page
     *
     *
     * @param quizName
     * @param courseId
     * @param quizId
     * @param redirectAttributes
     * @return Returns a String which points to the correct HTML file to be rendered
     */
    @RequestMapping(value = "/courses/{courseId}/{quizId}/editName", method = RequestMethod.POST)
    public String saveNewQuizName(@RequestParam String quizName, @RequestParam Long courseId, @RequestParam Long quizId, RedirectAttributes redirectAttributes) {

        System.out.println("\n\n\n\n\n\n\n\n\n\n  i was called  \n\n\n\n\n\n\n\n\n");

        Quiz quiz = quizService.findOne(quizId);
        quiz.setQuizName(quizName);

        quizService.save(quiz);

        String message = "Quiz name was changed to " + quizName;
        redirectAttributes.addFlashAttribute("flash",new FlashMessage(message, FlashMessage.Status.SUCCESS));

        return "redirect:/courses/" + courseId + "/" + quizId;

    }




}