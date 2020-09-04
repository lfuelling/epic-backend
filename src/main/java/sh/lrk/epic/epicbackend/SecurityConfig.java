package sh.lrk.epic.epicbackend;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user").password("{noop}password").roles("USER", "ADMIN");
                //.and()
                //.withUser("admin").password("{noop}password").roles("USER", "ADMIN");

    }

    // Secure the endpoins with HTTP Basic authentication
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.httpBasic()
            .and()
            .authorizeRequests()
            .antMatchers(HttpMethod.GET, "/image/**").hasRole("USER")
            .antMatchers(HttpMethod.GET, "/entry/**").hasRole("USER")
            .antMatchers(HttpMethod.GET, "/entries").hasRole("USER")
            .antMatchers(HttpMethod.POST, "/upload").hasRole("USER")
            .and()
            .csrf().disable()
            .formLogin().disable();
    }

}
