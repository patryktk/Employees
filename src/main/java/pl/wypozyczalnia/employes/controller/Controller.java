package pl.wypozyczalnia.employes.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.wypozyczalnia.employes.model.Employee;
import pl.wypozyczalnia.employes.repo.EmployeeRepo;

import java.security.Principal;
import java.util.List;

@CrossOrigin
@RestController
public class Controller {

    EmployeeRepo employeeRepo;

    public Controller(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    @GetMapping("/forSignIn")
    private String forSignIn(Principal principal) {
        return "Witaj, " + principal.getName() + " udało Ci się zalogować";
    }

    @GetMapping("/logout")
    public String logout() {
        return "papa ";
    }

    @GetMapping("/getSalesmans")
    public Employee getSalesmans() {
        return employeeRepo.findAllByRole("ROLE_SALESMAN");
    }

    @GetMapping("/getUserDetails")
    public List<Employee> getUserDetails() {
        return employeeRepo.findAll();
    }
}
