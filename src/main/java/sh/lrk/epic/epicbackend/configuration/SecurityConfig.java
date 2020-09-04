package sh.lrk.epic.epicbackend.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.ForwardLogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return new ForwardLogoutSuccessHandler("/login");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user").password(passwordEncoder().encode("password")).roles("USER", "ADMIN");
        //.and()
        //.withUser("admin").password("{noop}password").roles("USER", "ADMIN");

    }

    // Secure the endpoins with HTTP Basic authentication
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.httpBasic()
            .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/login*").permitAll()
                .antMatchers(HttpMethod.POST, "/login*").permitAll()
                .antMatchers(HttpMethod.GET, "/*.js", "/*.css").permitAll()
                .antMatchers(HttpMethod.GET, "/").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/image/**").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/entry/**").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/entries").hasRole("USER")
                .antMatchers(HttpMethod.POST, "/upload").hasRole("USER")
            .and()
                .csrf().disable()
                .formLogin().loginPage("/login").loginProcessingUrl("/login").failureUrl("/login").defaultSuccessUrl("/", true).permitAll()
            .and()
                .exceptionHandling().accessDeniedPage("/no-access")
            .and()
                .logout()
                .logoutUrl("/logout")
                .deleteCookies("JSESSIONID")
                .logoutSuccessHandler(logoutSuccessHandler());
    }

}
