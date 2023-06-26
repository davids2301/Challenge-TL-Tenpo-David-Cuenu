package cl.com.tenpo;

import cl.com.tenpo.application.port.out.ICallsHistoryPortOut;
import cl.com.tenpo.domain.configuration.exception.TenpoException;
import cl.com.tenpo.domain.dto.CallsHistoryDTO;
import cl.com.tenpo.domain.enumeration.CallsHistoryEnumeration;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CallsHistoryAdapterOutTest {

    @Autowired
    private ICallsHistoryPortOut iCallsHistoryPortOut;

    @Test
    @Order(8)
    @DisplayName("CALLS HISTORY PAGINATED")
    public void calls_history_paginated() throws TenpoException {

        System.out.println(":::::::::::::::::  STORE CALLS HISTORY PAGINATED STARTED  ::::::::::::::::: ");

        // We defined number of record that we wish
        final int numberOfRecords=2;

        // We query calls history paginated
        List<CallsHistoryDTO> callsHistoryModelList=iCallsHistoryPortOut.callsHistoryPaginated(0, 2,false, CallsHistoryEnumeration.ID.getValue());

        // We verify that is not null
        Assertions.assertNotNull(callsHistoryModelList);

        // We verify list size
        Assertions.assertEquals(numberOfRecords, callsHistoryModelList.size());

        //We are printing records
        callsHistoryModelList.forEach(x->System.out.println("Call History Item: "+x));

        System.out.println(":::::::::::::::::  STORE CALLS HISTORY PAGINATED ENDED  ::::::::::::::::: ");
    }

}
