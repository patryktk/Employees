package pl.wypozyczalnia.employes;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

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
//        User userAdmin = new User("PiotrekAdmin", getPasswordEncoder().encode("PiotrekAdmin"), Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN")));
//        User userUser = new User("PiotrekUser", getPasswordEncoder().encode("PiotrekUser"), Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")));
//        auth.inMemoryAuthentication().withUser(userAdmin);
//        auth.inMemoryAuthentication().withUser(userUser);

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
                .antMatchers("/role").hasRole("MANAGER")
                .and()
                .formLogin().loginPage("/login").defaultSuccessUrl("/forSignIn").permitAll()
                .and()
                .logout().logoutSuccessUrl("/logout");
    }

}
