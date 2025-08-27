package co.com.auth.businessconfiguration;

import co.com.auth.commons.exception.TechnicalException;
import co.com.auth.commons.exception.TechnicalExceptionMessage;
import co.com.auth.model.gateway.BusinessConfigurationGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class BusinessConfigurationAdapter implements BusinessConfigurationGateway {

    private final BusinessConfigurationProperties properties;

    @Override
    public BigDecimal getMinBaseSalary() {
        int minBaseSalary = properties.getMinBaseSalary();
        if (minBaseSalary < 0 ) {
            throw new TechnicalException(TechnicalExceptionMessage.CONFIGURATION_NOT_FOUND);
        }
        return BigDecimal.valueOf(minBaseSalary);
    }
}
