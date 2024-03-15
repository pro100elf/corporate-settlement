package ru.pogornev.course.taskspring2;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.pogornev.course.taskspring2.model.TpRequest;
import java.sql.Timestamp;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TppAgreementTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testCreateCorporateSettlementInstance() {
        TpRequest instance = new TpRequest();
        instance.setInstanceId(37);
        instance.setProductType("ЕЖО");
        instance.setContractNumber("345/qwerty");
        instance.setContractDate(Timestamp.valueOf("2024-03-15 00:00:00"));
        ResponseEntity<Void> response = restTemplate.postForEntity("/corporate-settlement-instance/create", instance, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
