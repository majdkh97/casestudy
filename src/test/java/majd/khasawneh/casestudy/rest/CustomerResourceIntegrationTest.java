package majd.khasawneh.casestudy.rest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CustomerResourceIntegrationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void getCumulativeBalance(){
        // Arrange
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");

        HttpEntity requestEntity = new HttpEntity(null, headers);

        // Act
        ResponseEntity<Double> response = testRestTemplate.exchange("/customer/balance/1",
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<>() {
                });

        // Assert
        Assertions.assertEquals(1000D, response.getBody().doubleValue());
    }

    @Test
    void getMonthlyBalance(){
        // Arrange
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");

        HttpEntity requestEntity = new HttpEntity(null, headers);

        // Act
        ResponseEntity<Double> response = testRestTemplate.exchange("/customer/balance/1/month?month=may",
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<>() {
                });

        // Assert
        Assertions.assertEquals(1500D, response.getBody().doubleValue());
    }
}