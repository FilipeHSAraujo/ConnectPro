package ConnectPro.com.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;



@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);

        return encoder;

    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf((csrf) -> csrf.disable())

                .httpBasic(Customizer.withDefaults())

                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/posts/**").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/users/**").authenticated()
                        .requestMatchers("/users/**").permitAll()

                .anyRequest().permitAll());

        return http.build();

    }

    @Bean

    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails user = org.springframework.security.core.userdetails.User
                .withUsername("devUser")
                .password(passwordEncoder.encode("devPass"))
                .authorities("ROLE_USER")
                .build();

        return new InMemoryUserDetailsManager(user);


    }

}
