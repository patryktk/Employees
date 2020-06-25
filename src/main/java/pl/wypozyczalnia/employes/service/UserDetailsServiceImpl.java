package pl.wypozyczalnia.employes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.wypozyczalnia.employes.repo.EmployeeRepo;

@Primary
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private EmployeeRepo employeeRepo;

    @Autowired
    public UserDetailsServiceImpl(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return employeeRepo.findAllByUsername(s);
    }


}
