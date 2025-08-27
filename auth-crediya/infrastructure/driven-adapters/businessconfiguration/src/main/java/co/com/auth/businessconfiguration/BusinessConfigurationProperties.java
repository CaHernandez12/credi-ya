package co.com.auth.businessconfiguration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("business-configuration")
public class BusinessConfigurationProperties {

    private int minBaseSalary;

}
