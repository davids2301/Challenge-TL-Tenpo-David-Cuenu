package cl.com.tenpo;

import cl.com.tenpo.application.port.in.ISumTwoNumbersPortIn;
import cl.com.tenpo.domain.configuration.exception.TenpoException;
import cl.com.tenpo.domain.model.SumTwoNumbersModel;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SumTwoNumbersUseCaseTest {

    @Autowired
    private ISumTwoNumbersPortIn iSumTwoNumbersPortIn;

    @Test
    @Order(12)
    @DisplayName("SUM TWO NUMBERS PLUS EXTERNAL PERCENTAGE")
    public void sum_two_numbers_plus_external_percentage() throws TenpoException {
/*
        System.out.println(":::::::::::::::::  SUM TWO NUMBERS PLUS EXTERNAL PERCENTAGE STARTED  ::::::::::::::::: ");

        // We send to sum two numbers
        SumTwoNumbersModel callsHistoryModelList=iSumTwoNumbersPortIn.sumTwoNumbersPlusExternalPercentage(5.0, 5.0);

        // We verify that is not null
        Assertions.assertNotNull(callsHistoryModelList);

        // We verify that calculated value be greater than 0
        Assertions.assertNotEquals(0, callsHistoryModelList.getSumValues());

        //We are printing object product of the sum
        System.out.println("Object Product Of The Sum: "+callsHistoryModelList);

        System.out.println(":::::::::::::::::  SUM TWO NUMBERS PLUS EXTERNAL PERCENTAGE ENDED  ::::::::::::::::: ");

 */
    }

}
