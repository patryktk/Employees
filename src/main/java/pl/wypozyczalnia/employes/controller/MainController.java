package pl.wypozyczalnia.employes.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.wypozyczalnia.employes.model.Employee;
import pl.wypozyczalnia.employes.repo.EmployeeRepo;
import pl.wypozyczalnia.employes.service.UserService;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@CrossOrigin
@Controller
public class MainController {

    private UserService userService;
    EmployeeRepo employeeRepo;


    public MainController(UserService userService, EmployeeRepo employeeRepo) {
        this.userService = userService;
        this.employeeRepo = employeeRepo;
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/signUp")
    public ModelAndView signUp(){

        return new ModelAndView("registration", "user", new Employee());
    }

    @RequestMapping("/register")
    public ModelAndView register(Employee user, HttpServletRequest request,
                                 @RequestParam(defaultValue = "false")boolean adminCheckBox,
                                 @RequestParam(defaultValue = "false")boolean managerCheckBox) throws MessagingException {

        userService.addNewUser(user, request);
        if(adminCheckBox) userService.addNewAdmin(user,request);
        if(managerCheckBox) userService.addNewManager(user, request);
        return new ModelAndView("redirect:/login");
    }

    @RequestMapping("/verify-token")
    public ModelAndView verifyToken (@RequestParam String token){
        userService.verifyToken(token);
        return new ModelAndView("redirect:/login");

    }

    @RequestMapping("/verify-admin")
    public ModelAndView verifyAdminToken (@RequestParam String token){
        userService.verifyAdminToken(token);
        return new ModelAndView("redirect:/login");
    }
    @RequestMapping("/verify-manager")
    public ModelAndView verifyManagerToken (@RequestParam String token){
        userService.verifyManagerToken(token);
        return new ModelAndView("redirect:/login");
    }



}
