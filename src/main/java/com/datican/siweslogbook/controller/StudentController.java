package com.datican.siweslogbook.controller;

import com.datican.siweslogbook.model.Student;
import com.datican.siweslogbook.repository.StudentRepository;
import com.datican.siweslogbook.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;


@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;
//    @Autowired
//    private LogbookEntryRepository logbookEntryRepository;
    private final StudentService studentService;

    public  StudentController(StudentService authService){
        this.studentService = authService;
    }

    @GetMapping("/")
    public String home() {
        return "index";  // This assumes your index.html is in the static folder
    }
    @GetMapping("/register")
    public String getStudentRegisterPage(Model model) {
        System.out.println("Accessing Student Register Page");
        model.addAttribute("registerRequest", new Student());
        return "register";
    }
    @GetMapping("/login")
    public String getStudentLoginPage(Model model) {
        System.out.println("Accessing Student Login Page");
        model.addAttribute("loginRequest", new Student());
        return "student-login";
    }
    @PostMapping("/submit-logbook")
    public ModelAndView submitLogbook(@RequestParam Map<String, String> entries) {
        // Process the logbook entries (e.g., save to the database)
        // For example, saveEntries(entries);

        // After processing, redirect to the student dashboard
        return new ModelAndView("redirect:/student-dashboard");
    }
    @PostMapping("/register")
    public String register(@ModelAttribute Student student, Model model) {
        System.out.println("Register request: " + student);

        Student registeredUser = studentService.registerUser(
                student.getMatricNumber(),
                student.getFirstName(),
                student.getMiddleName(),
                student.getLastName(),
                student.getPhoneNumber(),
                student.getEmail(),
                student.getResidentialAddress(),
                student.getPassword(),
                student.getFaculty(),
                student.getDepartment(),
                student.getCompanyName(),
                student.getCompanyLocation(),
                student.getCompanyCustomerCare(),
                student.getSupervisorId(),
                student.getSupervisorName(),
                student.getSupervisorPhone()
        );

        if (registeredUser == null) {
            model.addAttribute("errorMessage", "Registration failed. Please try again.");
            return "register";  // Stay on the register page and show an error message
        } else {
            System.out.println("Registration successful, redirecting to login");
            return "redirect:/student/login";  // Redirect to the login page
        }
    }



//    @PostMapping("/login")
//    public String login(@ModelAttribute Student student, Model model) {
//        System.out.println("Login request: " + student);
//
//        Student authenticated = studentService.authenticate(student.getMatricNumber(), student.getPassword());
//
//        if (authenticated != null) {
//            System.out.println("Authentication successful. Redirecting to dashboard.");
//            model.addAttribute("student", authenticated);  // Pass student object to the view
//            return "student-dashboard";  // Ensure this file exists in the templates folder
//        } else {
//            System.out.println("Authentication failed. Showing error page.");
//            return "error_page";  // Ensure error_page.html exists in templates
//        }
//    }


    @PostMapping("/login")
    public String login(@ModelAttribute Student student, Model model) {
        System.out.println("Login request: " + student);

        Student authenticated = studentService.authenticate(
                student.getMatricNumber(), student.getPassword());

        if (authenticated != null) {
            System.out.println("Authentication successful. Redirecting to dashboard.");

            // Pass student data to the dashboard
            model.addAttribute("student", authenticated);
            return "student-dashboard";  // Make sure the template name matches
        } else {
            System.out.println("Authentication failed. Showing error page.");
            model.addAttribute("errorMessage", "Invalid login credentials.");
            return "student-login";  // Redirect back to login page
        }
    }


}
