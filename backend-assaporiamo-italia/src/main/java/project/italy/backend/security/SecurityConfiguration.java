package project.italy.backend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    @SuppressWarnings("removals")
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(requests -> requests
                .requestMatchers("/backoffice/prodotti/create", "/backoffice/prodotti/*/edit",
                        "/backoffice/prodotti/dettaglio/*/gestisci-vini", "/backoffice/vini/create",
                        "/backoffice/vini/*/edit",
                        "/backoffice/vini/dettaglio/*/gestisci-prodotti")
                .hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.POST, "/login", "/logout").permitAll()
                .requestMatchers(HttpMethod.POST, "/**").hasAuthority("ADMIN")
                .requestMatchers("/backoffice/prodotti/**",
                        "/backoffice/vini/**", "/**")
                .permitAll())
                // .formLogin(Customizer.withDefaults())
                .formLogin(form -> form
                        .loginPage("/login") // L'URL dove risiede il tuo form Bootstrap
                        .loginProcessingUrl("/login") // L'endpoint POST nativo gestito da Spring
                        .defaultSuccessUrl("/", true) // Dove reindirizzare dopo il successo
                        .permitAll())
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll())
                .cors(cors -> cors.disable())
                .csrf(csrf -> csrf.disable());
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    @SuppressWarnings("deprecated")
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(userDetailService());

        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    DatabaseUserDetailService userDetailService() {
        return new DatabaseUserDetailService();
    }
}
