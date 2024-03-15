package ru.pogornev.course.taskspring2;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.pogornev.course.taskspring2.model.TprRequest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TppProductRegisterControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testCreateCorporateSettlementAccount() {
        var tprRequest = new TprRequest();
        tprRequest.setInstanceId(12);
        tprRequest.setRegistryTypeCode("03.012.002_47533_ComSoLd");
        tprRequest.setBranchCode("0022");
        tprRequest.setCurrencyCode("800");
        tprRequest.setMdmCode("15");
        tprRequest.setPriorityCode("00");
        ResponseEntity<Void> response = restTemplate.postForEntity("/corporate-settlement-account/create", tprRequest, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
