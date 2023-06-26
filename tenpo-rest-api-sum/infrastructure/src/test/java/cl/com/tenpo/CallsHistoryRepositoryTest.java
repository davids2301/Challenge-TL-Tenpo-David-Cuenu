package cl.com.tenpo;

import cl.com.tenpo.domain.configuration.exception.TenpoException;
import cl.com.tenpo.domain.entity.CallsHistoryEntity;
import cl.com.tenpo.domain.repository.ICallsHistoryRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CallsHistoryRepositoryTest {

    @Autowired
    private ICallsHistoryRepository iCallsHistoryRepository;

    @Test
    @Order(1)
    @DisplayName("STORE CALL HISTORY STARTED")
    public void store_call_history() throws TenpoException {

        System.out.println(":::::::::::::::::  STORE CALL HISTORY STARTED  ::::::::::::::::: ");

        // We create an array list for simulate reals insertions of history
        List<CallsHistoryEntity> callsHistoryModelList=new ArrayList<>();

        // We populate the array list
        callsHistoryModelList.add(CallsHistoryEntity.builder().request("request").response("response").dateAndHour(LocalDateTime.now()).build());
        callsHistoryModelList.add(CallsHistoryEntity.builder().request("request").response("response").dateAndHour(LocalDateTime.now()).build());
        callsHistoryModelList.add(CallsHistoryEntity.builder().request("request").response("response").dateAndHour(LocalDateTime.now()).build());

        // Assertion not null verify that all insert be executed
        callsHistoryModelList.forEach(x-> Assertions.assertNotNull(iCallsHistoryRepository.save(x)));

        System.out.println(":::::::::::::::::  STORE CALL HISTORY STARTED  ::::::::::::::::: ");
    }

    @Test
    @Order(2)
    @DisplayName("MODIFY CALL HISTORY")
    public void modify_call_history() throws TenpoException {

        System.out.println(":::::::::::::::::  MODIFY CALL HISTORY STARTED  ::::::::::::::::: ");

        // We look for record 2 stored on db
        Optional<CallsHistoryEntity> callsHistoryEntityOptional=iCallsHistoryRepository.findAll().stream().findAny();

        // We verify that callsHistoryEntityOriginal is not empty
        Assertions.assertFalse(callsHistoryEntityOptional.isEmpty());

        CallsHistoryEntity callsHistoryEntityOriginal=callsHistoryEntityOptional.get();

        // We create object that will modify record 2
        CallsHistoryEntity callsHistoryEntityToModify=CallsHistoryEntity.builder().id(callsHistoryEntityOriginal.getId()).request("request-modify").response(callsHistoryEntityOriginal.getResponse()).dateAndHour(callsHistoryEntityOriginal.getDateAndHour()).build();

        // We modify record from db
        CallsHistoryEntity callsHistoryEntityModified = iCallsHistoryRepository.save(callsHistoryEntityToModify);

        // We verify that record list be not null
        Assertions.assertNotEquals(callsHistoryEntityOriginal, callsHistoryEntityModified);

        //We are print original record
        System.out.println("Call History Item Original: "+callsHistoryEntityOriginal);

        //We are print original record
        System.out.println("Call History Item Modified: "+callsHistoryEntityModified);

        System.out.println(":::::::::::::::::  MODIFY CALL HISTORY ENDED  ::::::::::::::::: ");
    }

    @Test
    @Order(3)
    @DisplayName("GET ALL CALL HISTORY")
    public void get_all_call_history() throws TenpoException {

        System.out.println(":::::::::::::::::  GET ALL CALL HISTORY STARTED  ::::::::::::::::: ");

        // We get all records from db
        List<CallsHistoryEntity> callsHistoryEntityList = iCallsHistoryRepository.findAll();

        // We verify that record list be not null
        Assertions.assertNotNull(callsHistoryEntityList);

        //We are printing records
        callsHistoryEntityList.forEach(x->System.out.println("Call History Item: "+x));

        System.out.println(":::::::::::::::::  GET ALL CALL HISTORY ENDED  ::::::::::::::::: ");
    }

    @Test
    @Order(4)
    @DisplayName("DELETE CALL HISTORY")
    public void delete_call_history() throws TenpoException {

        System.out.println(":::::::::::::::::  DELETE CALL HISTORY STARTED  ::::::::::::::::: ");

        // We delete record from db
        iCallsHistoryRepository.deleteById(1);

        // We print record to in from db
        System.out.println("Record to delete: "+1);

        // We get all records from db
        List<CallsHistoryEntity> callsHistoryEntityList = iCallsHistoryRepository.findAll();

        // We verify that record list be not null
        Assertions.assertNotNull(callsHistoryEntityList);

        //We are printing records
        callsHistoryEntityList.forEach(x->System.out.println("Call History Item: "+x));

        System.out.println(":::::::::::::::::  DELETE CALL HISTORY ENDED  ::::::::::::::::: ");
    }
    
}