package vignesh.controller;

import vignesh.model.Feedback;
import vignesh.service.UserService;

import vignesh.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/signup")
    public String showSignupPage() {
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@RequestParam String name,
                         @RequestParam String email,
                         @RequestParam String password,
                         @RequestParam String confirmPassword,
                         Model model) {
        String message = userService.registerUser(name, email, password, confirmPassword);
        if (message.equals("Registration successful!")) {
            model.addAttribute("message", message);
            return "redirect:/login";
        } else {
            model.addAttribute("error", message);
            return "signup";
        }
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password,
                        Model model) {
        User user = userService.loginUser(email, password);
        if (user != null) {
            model.addAttribute("message", "Login Successful!");
            return "home"; // Redirect to welcome page
        } else {
            model.addAttribute("error", "Invalid email or password!");
            return "login";
        }
    }
    @GetMapping("/home")
    public String home(){
        return "home";
    }
    @GetMapping("/feedback-hub")
    public String showFeedbackForm(Model model) {
        model.addAttribute("feedback", new Feedback());
        return "feedback-hub"; // This refers to feedback-form.html
    }

    @PostMapping("/submit-feedback")
    public String submitFeedback(@ModelAttribute Feedback feedback, Model model) {
        // You can save this feedback to a database here (if needed)
        model.addAttribute("message", "Thank you for your feedback, " + feedback.getName() + "!");
        return "feedback-success"; // A success page
    }
}
