package co.com.oncredit.config;

import org.junit.jupiter.api.Test;
import org.reactivecommons.utils.ObjectMapper;
import org.reactivecommons.utils.ObjectMapperImp;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ObjectMapperLoanTypeConfigTest {

    @Test
    void testObjectMapperBean() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ObjectMapperConfig.class);
        ObjectMapper objectMapper = context.getBean(ObjectMapper.class);
        assertNotNull(objectMapper);
        assertTrue(objectMapper instanceof ObjectMapperImp);
        context.close();
    }
}