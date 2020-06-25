package pl.wypozyczalnia.employes.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wypozyczalnia.employes.model.VerificationToken;

public interface VerificationTokenRepo extends JpaRepository<VerificationToken, Long> {

    VerificationToken findByValue(String value);

}
