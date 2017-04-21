package com.ChiliPrepper.ChiliPrepper.web.controller;

import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.security.Principal;
import org.springframework.ui.Model;
import com.ChiliPrepper.ChiliPrepper.model.*;
import com.ChiliPrepper.ChiliPrepper.service.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import com.ChiliPrepper.ChiliPrepper.web.FlashMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by Andreas on 19.02.2017.
 *
 * The @Controller annotation lets Spring know that this is a controller.
 *
 *Controller classes handles URI requests from the browser with methods marked with the
 * @RequestMapping annotation.
 *
 * These methods return a String with the name of which HTML file to render from the
 * templates directory. Various objects or variables may be added to, or read from, the model.
 * (adding something to the model is like adding something to that particular HTML file rendering).
 */

@Controller
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private UserService userService;

    @Autowired
    private QuizService quizService;

    @Autowired
    private AnswerService answerService;

    @Autowired
    private QuestionService questionService;

    @RequestMapping("/")
    public String renderIndexView(Model model, Principal principal) {

        String username = principal.getName();
        User user = userService.findByUsername(username);
        Set<Course> regCourses = user.getRegCourses();
        Iterable<Course> myCourses = courseService.findAllForCreator();

        model.addAttribute("myCourses", myCourses);
        model.addAttribute("course", new Course());
        model.addAttribute("regCourses", regCourses);

        return "index";
    }

    @RequestMapping("/courses/{courseId}")
    public String renderCourseView(@PathVariable Long courseId, Model model, Principal principal){

        String username = principal.getName();
        User user = userService.findByUsername(username);
        Course course = courseService.findOne(courseId);
        User creator = course.getCreator();
        Iterable<Quiz> myQuizzes = quizService.findAllByCourse_id(courseId);

        model.addAttribute("course", course);
        model.addAttribute("quiz", new Quiz());
        model.addAttribute("courseId", courseId);
        model.addAttribute("myQuizzes", myQuizzes);
        model.addAttribute("userId", user.getId());
        model.addAttribute("creatorId", creator.getId());

        Iterable<Answer> answers = answerService.findAllByCourse_IdAndUser_Id(courseId, user.getId());
        List<Answer> numAnswers = new ArrayList<>();
        List<Answer> numCorrectAnswers = new ArrayList<>();
        answers.forEach(numAnswers :: add);

        for (Answer answer : answers){
            if(answer.isCorrect()){
                numCorrectAnswers.add(answer);
            }
        }

        Iterable<Answer> totalAnswers = answerService.findAllByCourse_Id(courseId);
        List<Answer> totalNumAnswers = new ArrayList<>();
        totalAnswers.forEach(totalNumAnswers :: add);

        Iterable<Quiz> quizzes =  quizService.findAllByCourse_id(courseId);
        ArrayList<Question> totalQuestions = new ArrayList<>();

        for (Quiz quiz : quizzes){
            Iterable<Question> quizQuestions = questionService.findAllByQuiz_Id(quiz.getId());
            quizQuestions.forEach(totalQuestions::add);
        }

        double userScore = numCorrectAnswers.size() * 10;
        double maxScore = totalQuestions.size()*10;

        model.addAttribute("score", userScore);
        model.addAttribute("maxScore", maxScore);

        return "course";
    }

    @RequestMapping(path = "/addCourse", method = RequestMethod.POST)
    public String addCourse(@ModelAttribute Course course, Principal principal, RedirectAttributes redirectAttributes) {

        String username = principal.getName();
        User user = userService.findByUsername(username);

        if((course.getCourseName().isEmpty() || course.getAccessCode().isEmpty())){
            String message = "Could not create the course. Name and accessCode cannot be empty!";
            redirectAttributes.addFlashAttribute("flash",new FlashMessage(message, FlashMessage.Status.FAILURE));
            return "redirect:/";
        }

        course.setCreator(user);
        courseService.save(course);

        String message = "You've successfully created " + course.getCourseName() + "!";
        redirectAttributes.addFlashAttribute("flash",new FlashMessage(message, FlashMessage.Status.SUCCESS));

        return "redirect:/";
    }

    @RequestMapping(path = "/regCourse", method = RequestMethod.POST)
    public String registerForCourse (Principal principal, @RequestParam String accessCode, RedirectAttributes redirectAttributes){

        String username = principal.getName();
        User user = userService.findByUsername(username);

        if(courseService.findByAccessCode(accessCode) != null){

            Course course = courseService.findByAccessCode(accessCode);
            Set<User> regUsers = course.getRegUsers();
            Set<Course> regCourses = user.getRegCourses();

            regUsers.add(user);
            regCourses.add(course);
            course.setRegUsers(regUsers);
            user.setRegCourses(regCourses);

            userService.save(user);
            courseService.save(course);

            String message = "You've registered in " + course.getCourseName() + "!";
            redirectAttributes.addFlashAttribute("flash",new FlashMessage(message, FlashMessage.Status.SUCCESS));

            return "redirect:/";
        }
        else{
            String message = "Registration failed! No course with that access code found!";
            redirectAttributes.addFlashAttribute("flash",new FlashMessage(message, FlashMessage.Status.FAILURE));

            return "redirect:/";
        }
    }


    @RequestMapping(path = "/courses/{courseId}/chart", method = RequestMethod.GET)
    public String renderCourseChartDisplayView(@PathVariable Long courseId, Model model){

        model.addAttribute("courseId", courseId);

        return "courseChartDisplay";
    }

    @RequestMapping(value = "/courseChart/{courseId}", method = RequestMethod.GET)
    public String getCourseChart(Model model, @PathVariable Long courseId) {

        Iterable<Quiz> quizzes = quizService.findAllByCourse_id(courseId);
        ArrayList<Double> results = getCourseResults(quizzes);
        String courseName = courseService.findOne(courseId).getCourseName();

        model.addAttribute("courseName", courseName);
        model.addAttribute("results", results);

        return "courseChart:: courseChart";
    }



    public ArrayList<Double> getCourseResults(Iterable<Quiz> quizzes) {
        ArrayList<Double> results = new ArrayList<>();

        for (Quiz quiz : quizzes){

            if(getAvgScoreForCourseChart(quiz.getId()) != null){
                results.add(getAvgScoreForCourseChart(quiz.getId()));
            }
            else{
                results.add(0.0);
            }
        }

        return results;
    }

    public Double getAvgScoreForCourseChart(Long quizId)  {
        Iterable<Answer> tAnswers = answerService.findAllByQuiz_Id(quizId);
        List<Answer> nAnswers = new ArrayList<>();
        List<Answer> nCorrectAnswers = new ArrayList<>();
        tAnswers.forEach(nAnswers::add);

        for (Answer answer : tAnswers) {
            if (answer.isCorrect()) {
                nCorrectAnswers.add(answer);
            }
        }

        try{
            return (double) (nCorrectAnswers.size() * 100 / nAnswers.size());
        }

        catch(ArithmeticException ae){
            System.out.println(ae.getMessage());
            return null;
        }
    }
}