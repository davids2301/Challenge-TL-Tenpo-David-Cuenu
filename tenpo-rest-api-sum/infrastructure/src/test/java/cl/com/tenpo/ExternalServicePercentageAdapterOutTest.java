package cl.com.tenpo;

import cl.com.tenpo.application.port.out.IExternalServicePercentagePortOut;
import cl.com.tenpo.domain.configuration.exception.TenpoException;
import cl.com.tenpo.domain.dto.ExternalPercentageDTO;
import cl.com.tenpo.domain.entity.CallsHistoryEntity;
import cl.com.tenpo.domain.repository.ICallsHistoryRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ExternalServicePercentageAdapterOutTest {

    @Autowired
    private IExternalServicePercentagePortOut iExternalServicePercentagePortOut;

    @Autowired
    private ICallsHistoryRepository iCallsHistoryRepository;

    @Test
    @Order(10)
    @DisplayName("SUM TWO NUMBERS PLUS EXTERNAL PERCENTAGE")
    public void sum_two_numbers_plus_External_percentage() throws TenpoException {
    /*
        // Assertion not null verify that all insert be executed
        CallsHistoryEntity.builder().request("request").response("response").dateAndHour(LocalDateTime.now()).build();

        System.out.println(":::::::::::::::::  SUM TWO NUMBERS PLUS EXTERNAL PERCENTAGE  ::::::::::::::::: ");

        // We send to sum two numbers
        ExternalPercentageDTO externalPercentageDTO=iExternalServicePercentagePortOut.sumTwoNumbersPlusExternalPercentage(5.0, 5.0);

        // We verify that is not null
        Assertions.assertNotNull(externalPercentageDTO);

        // We verify that calculated value be greater than 0
        Assertions.assertNotEquals(0, externalPercentageDTO.getCalculatedValue());

        //We are printing object product of the sum
        System.out.println("Object Product Of The Sum: "+externalPercentageDTO);

        System.out.println(":::::::::::::::::  SUM TWO NUMBERS PLUS EXTERNAL PERCENTAGE  ::::::::::::::::: ");

     */
    }

}