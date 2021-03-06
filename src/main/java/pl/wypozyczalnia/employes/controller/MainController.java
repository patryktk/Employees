package pl.wypozyczalnia.employes.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import pl.wypozyczalnia.employes.model.Employee;
import pl.wypozyczalnia.employes.repo.EmployeeRepo;
import pl.wypozyczalnia.employes.service.UserService;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
public class MainController {

    EmployeeRepo employeeRepo;
    private UserService userService;


    public MainController(UserService userService, EmployeeRepo employeeRepo) {
        this.userService = userService;
        this.employeeRepo = employeeRepo;
    }

    @RequestMapping("/login")
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping("/signUp")
    public ModelAndView signUp() {

        return new ModelAndView("registration", "user", new Employee());
    }

    @RequestMapping("/register")
    public ModelAndView register(Employee user, HttpServletRequest request,
                                 @RequestParam(defaultValue = "false") boolean adminCheckBox,
                                 @RequestParam(defaultValue = "false") boolean managerCheckBox) throws MessagingException {

        userService.addNewUser(user, request);
        if (adminCheckBox) userService.addNewAdmin(user, request);
        if (managerCheckBox) userService.addNewManager(user, request);
        return new ModelAndView("redirect:/login");
    }

    @RequestMapping("/verify-token")
    public ModelAndView verifyToken(@RequestParam String token) {
        userService.verifyToken(token);
        return new ModelAndView("redirect:/login");

    }

    @RequestMapping("/verify-admin")
    public ModelAndView verifyAdminToken(@RequestParam String token) {
        userService.verifyAdminToken(token);
        return new ModelAndView("redirect:/login");
    }

    @RequestMapping("/verify-manager")
    public ModelAndView verifyManagerToken(@RequestParam String token) {
        userService.verifyManagerToken(token);
        return new ModelAndView("redirect:/login");
    }


}
