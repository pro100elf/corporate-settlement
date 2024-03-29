package ru.pogornev.course.taskspring2;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.pogornev.course.taskspring2.model.TpRequest;
import java.math.BigDecimal;
import java.sql.Timestamp;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TppProductControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testCreateCorporateSettlementInstance() {
        TpRequest instance = new TpRequest();
        instance.setProductCode(12345l);
        instance.setProductType("НСО");
        instance.setContractNumber("345/qwerty");
        instance.setPriority(2);
        instance.setContractDate(Timestamp.valueOf("2024-03-14 00:00:00"));
        instance.setInterestRatePenalty(0.25f);
        instance.setThresholdAmount(BigDecimal.valueOf(23456789.00));
        instance.setRateType("0");
        instance.setTaxPercentageRate(13f);
        instance.setProductCodeName("03.012.002");
        instance.setBranchCode("0022");
        instance.setCurrencyCode("800");
        instance.setMdmCode("15");
        instance.setPriorityCode("00");
        ResponseEntity<Void> response = restTemplate.postForEntity("/corporate-settlement-instance/create", instance, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
