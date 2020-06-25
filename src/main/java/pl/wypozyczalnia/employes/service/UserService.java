package pl.wypozyczalnia.employes.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.wypozyczalnia.employes.model.Employee;
import pl.wypozyczalnia.employes.model.VerificationToken;
import pl.wypozyczalnia.employes.repo.EmployeeRepo;
import pl.wypozyczalnia.employes.repo.VerificationTokenRepo;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Service
public class UserService {

    private EmployeeRepo employeeRepo;
    private PasswordEncoder passwordEncoder;
    private VerificationTokenRepo verificationTokenRepo;
    private MailSenderService mailSenderService;

    public UserService(EmployeeRepo employeeRepo, PasswordEncoder passwordEncoder, VerificationTokenRepo verificationTokenRepo, MailSenderService mailSenderService) {
        this.employeeRepo = employeeRepo;
        this.passwordEncoder = passwordEncoder;
        this.verificationTokenRepo = verificationTokenRepo;
        this.mailSenderService = mailSenderService;
    }

    public void addNewUser(Employee user, HttpServletRequest request) throws MessagingException {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        employeeRepo.save(user);
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken(user, token);
        verificationTokenRepo.save(verificationToken);

        String url = request.getServerName() + ":" +
                request.getServerPort() +
                request.getContextPath() +
                "/verify-token?token=" + token;

        mailSenderService.sendMail(user.getUsername(), "VerificationToken", url, false);
    }

    public void verifyToken(String token) {
        Employee employee = verificationTokenRepo.findByValue(token).getEmployee();
        employee.setEnabled(true);
        employee.setRole("ROLE_MECHANIC");
        employeeRepo.save(employee);
    }

    public void addNewAdmin(Employee user, HttpServletRequest request) throws MessagingException {
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken(user, token);
        verificationTokenRepo.save(verificationToken);

        String url = request.getServerName() + ":" +
                request.getServerPort() +
                request.getContextPath() +
                "/verify-admin?token=" + token;

        mailSenderService.sendMail("mailsenderspring@gmail.com", "SalesMan request:" + user.getUsername(), url, false);
    }

    public void verifyAdminToken(String token) {
        Employee employee = verificationTokenRepo.findByValue(token).getEmployee();
        employee.setEnabled(true);
        employee.setRole("ROLE_SALESMAN");
        employeeRepo.save(employee);
    }

    public void addNewManager(Employee user, HttpServletRequest request) throws MessagingException {
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken(user, token);
        verificationTokenRepo.save(verificationToken);

        String url = request.getServerName() + ":" +
                request.getServerPort() +
                request.getContextPath() +
                "/verify-manager?token=" + token;

        mailSenderService.sendMail("mailsenderspring@gmail.com", "Manager request:" + user.getUsername(), url, false);
    }

    public void verifyManagerToken(String token) {
        Employee employee = verificationTokenRepo.findByValue(token).getEmployee();
        employee.setEnabled(true);
        employee.setRole("ROLE_MANAGER");
        employeeRepo.save(employee);
    }
}
