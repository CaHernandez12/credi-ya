package co.com.auth.api.authentication.security;

import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.ReactiveAuthenticationManagerResolver;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;

public class JwtAuthenticationFilter extends AuthenticationWebFilter {

    public JwtAuthenticationFilter(ReactiveAuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    public JwtAuthenticationFilter(ReactiveAuthenticationManagerResolver authenticationManagerResolver) {
        super(authenticationManagerResolver);
    }

}
