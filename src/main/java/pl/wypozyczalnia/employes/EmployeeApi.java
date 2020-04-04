package pl.wypozyczalnia.employes;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class EmployeeApi
{
    @GetMapping("/forNotAuthorized")
    public String forAll(Principal principal) {
        if (principal == null) {
            return "Czesc nieznajomy";
        } else return "Czesc " + principal.getName();
    }

    @GetMapping("/forMechanic")
    public String forMechanic(Principal principal) {
        return "Czesc user " + principal.getName();
    }

    @GetMapping("/forSalesman")
    public String forSalesman(Principal principal) {
        return "Czesc admin " + principal.getName();
    }

    @GetMapping("/forManager")
    public String forManager(Principal principal) {
        return "Czesc admin " + principal.getName();
    }

    @GetMapping("/papa")
    public String logout() {
        return "papa ";
    }

}
