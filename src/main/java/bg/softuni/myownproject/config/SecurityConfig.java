package bg.softuni.myownproject.config;


import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeHttpRequests(
                        authorizeRequests ->
                                authorizeRequests
                                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                                        .requestMatchers("/", "/users/login", "/users/login-error", "/users/register", "/about", "/membership", "/facilities/**" ).permitAll()
                                        .requestMatchers("/teams").hasAnyRole("MODERATOR", "ADMIN")
                                        .requestMatchers("/add-team").hasAnyRole("MODERATOR", "ADMIN")
                                        .requestMatchers("/coaches").hasAnyRole("MODERATOR", "ADMIN")
                                        .requestMatchers("/training-sessions/add").hasAnyRole("MODERATOR", "ADMIN")
                                        .requestMatchers("/roles").hasRole("ADMIN")
                                        .anyRequest()
                                        .authenticated()
                )
                .formLogin(formLogin ->
                        formLogin
                                .loginPage("/users/login")
                                .usernameParameter("username")
                                .passwordParameter("password")
                                .defaultSuccessUrl("/", true)
                                .failureUrl("/users/login-error")
                )
                .logout(
                        logout ->
                                logout
                                        .logoutUrl("/users/logout")
                                        .logoutSuccessUrl("/users/login")
                                        .invalidateHttpSession(true)
                )
                .build();
    }
}
