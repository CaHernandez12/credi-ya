package co.com.auth.api.authentication;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@AllArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationManager jwtAuthenticationManager;

    @Bean
    public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http) throws Exception {

        JwtAuthenticationFilter jwtFilter = new JwtAuthenticationFilter(jwtAuthenticationManager);
        jwtFilter.setServerAuthenticationConverter(new JwtAuthenticationConverter());

        http.csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(exchange -> exchange
                        .pathMatchers("aapi/v1/reportes")
                        .hasAuthority("ADMIN")
                )
                .addFilterAt(jwtFilter, SecurityWebFiltersOrder.AUTHENTICATION);
        return http.build();
    }
}
