package pl.wypozyczalnia.employes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.wypozyczalnia.employes.model.Employee;
import pl.wypozyczalnia.employes.repo.EmployeeRepo;

@Component
public class Start {

    private PasswordEncoder passwordEncoder;
    private EmployeeRepo employeeRepo;


    @Autowired
    public Start(PasswordEncoder passwordEncoder, EmployeeRepo employeeRepo) {
        this.passwordEncoder = passwordEncoder;
        this.employeeRepo = employeeRepo;

        if (employeeRepo.findAllByUsername("mechanik") == null){
            Employee user = new Employee();
                user.setUsername("mechanik");
                user.setPassword(passwordEncoder.encode("mechanik"));
                user.setRole("ROLE_MECHANIC");
                user.setEnabled(true);
            employeeRepo.save(user);
        }
        if (employeeRepo.findAllByUsername("sprzedawca") == null){
            Employee user = new Employee();
            user.setUsername("sprzedawca");
            user.setPassword(passwordEncoder.encode("sprzedawca"));
            user.setRole("ROLE_SALESMAN");
            user.setEnabled(true);
            employeeRepo.save(user);
        }
        if (employeeRepo.findAllByUsername("manager") == null){
            Employee user = new Employee();
            user.setUsername("manager");
            user.setPassword(passwordEncoder.encode("manager"));
            user.setRole("ROLE_MANAGER");
            user.setEnabled(true);
            employeeRepo.save(user);
        }
    }
}
