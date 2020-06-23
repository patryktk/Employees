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

    @GetMapping("/forNotAuthorized")
    public String forNotAuthorized(Principal principal) {
        if (principal == null) {
            return "Czesc nieznajomy";
        } else return "Czesc " + principal.getName();
    }
    @GetMapping("/forSignIn")
    private String forSignIn(Principal principal){
        return "Witaj, " +principal.getName() + " udało Ci się zalogować";
    }

    @GetMapping("/forMechanic")
    public String forMechanic(Principal principal) {
        return "Czesc mechanik " + principal.getName();
    }

    @GetMapping("/forSalesman")
    public String forSalesman(Principal principal) {
        return "Czesc sprzedawca " + principal.getName();
    }

    @GetMapping("/forManager")
    public String forManager(Principal principal) {
        return "Czesc manager " + principal.getName();
    }

    @GetMapping("/logout")
    public String logout() {
        return "papa ";
    }

    @GetMapping("/getSalesmans")
    public Employee getSalesmans(){
        return employeeRepo.findAllByRole("ROLE_SALESMAN");
    }

    @GetMapping("/getUserDetails")
    public List<Employee> getUserDetails(){
        return employeeRepo.findAll();
    }
}
