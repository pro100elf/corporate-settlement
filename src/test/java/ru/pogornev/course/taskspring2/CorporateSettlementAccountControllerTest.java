package ru.pogornev.course.taskspring2;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.pogornev.course.taskspring2.model.CorporateSettlementAccount;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CorporateSettlementAccountControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testCreateCorporateSettlementAccount() {
        CorporateSettlementAccount account = new CorporateSettlementAccount();
        ResponseEntity<Void> response = restTemplate.postForEntity("/corporate-settlement-account/create", account, Void.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
