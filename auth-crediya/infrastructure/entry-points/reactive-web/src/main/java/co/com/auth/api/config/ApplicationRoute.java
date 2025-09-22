package co.com.auth.api.config;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "entries.reactive-web")
public class ApplicationRoute {

    @NotBlank
    private String user;

    @NotBlank
    private String solicited;

    @NotBlank
    private String auth;

    @NotBlank
    private String paginator;

}
