package pl.wypozyczalnia.employes.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.wypozyczalnia.employes.model.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {

    Employee findAllByUsername(String username);
    Employee findAllByRole(String role);
}
