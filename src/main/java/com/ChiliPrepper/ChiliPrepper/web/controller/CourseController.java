package com.ChiliPrepper.ChiliPrepper.web.controller;

import com.ChiliPrepper.ChiliPrepper.model.*;
import com.ChiliPrepper.ChiliPrepper.service.*;

import com.ChiliPrepper.ChiliPrepper.web.FlashMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
 *
 * This controller handles Course objects and views
 *
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




    /**
     *
     *Renders the index page (start page)
     *
     *
     * @param model
     * @param principal
     * @return Returns a String which points to the correct HTML file to be rendered
     */
    @RequestMapping("/")
    public String index(Model model, Principal principal) {

        Iterable<Course> myCourses = courseService.findAllForCreator();
        model.addAttribute("myCourses", myCourses);

        String username = principal.getName();
        User user = userService.findByUsername(username);

        Set<Course> regCourses = user.getRegCourses();

        //add courses user are registered in to the model
        model.addAttribute("regCourses", regCourses);

        model.addAttribute("course", new Course());

        return "index";
    }


    /**
     *
     *Renders the view for a single course page
     *
     *
     * @param courseId
     * @param model
     * @param principal
     * @return Returns a String which points to the correct HTML file to be rendered
     */
    @RequestMapping("/courses/{courseId}")
    public String course(@PathVariable Long courseId, Model model, Principal principal){
        String username = principal.getName();
        User user = userService.findByUsername(username);
        Course course = courseService.findOne(courseId);


        model.addAttribute("userId", user.getId());
        User creator = course.getCreator();
        model.addAttribute("creatorId", creator.getId());

        model.addAttribute("quiz", new Quiz());
        model.addAttribute("courseId", courseId);
        Iterable<Quiz> myQuizes = quizService.findAllByCourse_id(courseId);
        model.addAttribute("myQuizes", myQuizes);
        model.addAttribute("course", course);

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

        Iterable<Quiz> quizes =  quizService.findAllByCourse_id(courseId);
        ArrayList<Question> totalQuestions = new ArrayList<>();
        for (Quiz quiz : quizes){
            Iterable<Question> quizQuestions = questionService.findAllByQuiz_Id(quiz.getId());
            quizQuestions.forEach(totalQuestions::add);

        }


        model.addAttribute("score", numCorrectAnswers.size()*10);

        model.addAttribute("maxScore", totalQuestions.size()*10);
        return "course";
    }


    /**
     *
     *Saves a new course to the database
     *
     * Redirects to the same page
     *
     * @param course
     * @param principal
     * @param redirectAttributes
     * @return Returns a String which points to the correct HTML file to be rendered
     */
    @RequestMapping(path = "/addCourse", method = RequestMethod.POST)
    public String addCourse(@ModelAttribute Course course, Principal principal, RedirectAttributes redirectAttributes) {
        String username = principal.getName();
        User user = userService.findByUsername(username);

        if((course.getCourseName().isEmpty() || course.getAccessCode().isEmpty())){
            redirectAttributes.addFlashAttribute("flash",new FlashMessage("Could not create the course. Name and accessCode cannot be empty! ", FlashMessage.Status.FAILURE));
            return "redirect:/";
        }
        course.setCreator(user);
        courseService.save(course);
        redirectAttributes.addFlashAttribute("flash",new FlashMessage("You've successfully created " + course.getCourseName() + " !", FlashMessage.Status.SUCCESS));
        return "redirect:/";
    }


    /**
     *
     *Registers a user in a course
     *
     * Redirects to same page
     *
     *
     * @param principal
     * @param accessCode
     * @param redirectAttributes
     * @return Returns a String which points to the correct HTML file to be rendered
     */
    @RequestMapping(path = "/regCourse", method = RequestMethod.POST)
    public String regCourse (Principal principal, @RequestParam String accessCode, RedirectAttributes redirectAttributes){
        String username = principal.getName();
        User user = userService.findByUsername(username);

        if(courseService.findByAccessCode(accessCode) != null){

            Course course = courseService.findByAccessCode(accessCode);

            Set<User> regUsers = course.getRegUsers();
            regUsers.add(user);

            Set<Course> regCourses = user.getRegCourses();
            regCourses.add(course);

            course.setRegUsers(regUsers);
            user.setRegCourses(regCourses);

            userService.save(user);
            courseService.save(course);
            redirectAttributes.addFlashAttribute("flash",new FlashMessage("You've registered in " + course.getCourseName(), FlashMessage.Status.SUCCESS));
            return "redirect:/";
        }
        else{
            redirectAttributes.addFlashAttribute("flash",new FlashMessage("Registration failed! No course with that accessCode found.", FlashMessage.Status.FAILURE));
            return "redirect:/";
        }
    }


    /**
     *
     *Renders the courseChartDisplay view for a chart of course results
     *
     *
     * @param courseId
     * @param model
     * @return Returns a String which points to the correct HTML file to be rendered
     */
    @RequestMapping(path = "/courses/{courseId}/chart", method = RequestMethod.GET)
    public String courseChart(@PathVariable Long courseId, Model model){

        model.addAttribute("courseId", courseId);

        return "courseChartDisplay";
    }


    /**
     *
     * Feeds the html file containig the Javascript for creating a chart with data
     *
     *(Jquery code in the courseChartDisplay.html file calls this method every 2000ms to create a "live" chart)
     *
     *
     * @param model
     * @param courseId
     * @return Returns a String which points to the correct HTML file to be rendered (in this case just part of a html file)
     */
    @RequestMapping(value = "/courseChart/{courseId}", method = RequestMethod.GET)
    public String getCourseChart(Model model, @PathVariable Long courseId) {

        Iterable<Quiz> quizes = quizService.findAllByCourse_id(courseId);

        ArrayList<Double> results = getCourseResults(quizes);

        String courseName = courseService.findOne(courseId).getCourseName();
        model.addAttribute("courseName", courseName);

        model.addAttribute("results", results);

//TODO: fix "overlap" in HTML on reload of chart

        return "courseChart:: courseChart";
    }





    /**
     *
     *Gets average percentage scores for all quizes in a course
     *
     *
     *
     * @param quizes
     * @return ArrayList containing average score for quizes in course
     */
    public ArrayList<Double> getCourseResults(Iterable<Quiz> quizes) {
        ArrayList<Double> results = new ArrayList<>();

        for (Quiz quiz : quizes){

            if(getAvgQuizScoreForCourseChart(quiz.getId()) != null){
                results.add(getAvgQuizScoreForCourseChart(quiz.getId()));
            }
            else{
                results.add(0.0);
            }
        }

        return results;
    }





    /**
     *
     *gets the average percentage score for a quiz
     *
     *
     * @param quizId
     * @return (double)score og null
     */
    public Double getAvgQuizScoreForCourseChart(Long quizId)  {

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
            double coursePercentageScore = (nCorrectAnswers.size() * 100 / nAnswers.size());
            return coursePercentageScore;
        }
        catch(ArithmeticException ae){
            System.out.println(ae.getMessage());
            return null;
        }
    }

}