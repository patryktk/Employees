package pl.wypozyczalnia.employes;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsService userDetailsService;

    public WebSecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/forMechanic").hasRole("MECHANIC")
                .antMatchers("/forSalesman").hasRole("SALESMAN")
                .antMatchers("/forManager").hasRole("MANAGER")
                .antMatchers("/forNotAuthorized").permitAll()
                .antMatchers("/signUp").permitAll()
                .antMatchers("/getSalesmans").permitAll()

                .and()
                .formLogin().loginPage("/login").defaultSuccessUrl("/forSignIn").permitAll()
                .and()
                .logout().logoutSuccessUrl("/logout");
    }

}
