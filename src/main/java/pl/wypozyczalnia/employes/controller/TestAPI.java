package pl.wypozyczalnia.employes.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class TestAPI
{
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

    @GetMapping("/papa")
    public String logout() {
        return "papa ";
    }

}
