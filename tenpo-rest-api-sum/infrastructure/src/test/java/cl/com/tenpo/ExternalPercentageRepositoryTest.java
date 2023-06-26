package cl.com.tenpo;

import cl.com.tenpo.domain.configuration.exception.TenpoException;
import cl.com.tenpo.domain.configuration.redis.TestRedisConfiguration;
import cl.com.tenpo.domain.entity.ExternalPercentageEntity;
import cl.com.tenpo.domain.repository.IExternalPercentageRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {TestRedisConfiguration.class})
public class ExternalPercentageRepositoryTest {

    @Autowired
    private IExternalPercentageRepository iExternalPercentageRepository;

    @Test
    @Order(5)
    @DisplayName("STORE PERCENTAGE STARTED")
    public void store_percentage() throws TenpoException {

        System.out.println(":::::::::::::::::  STORE PERCENTAGE STARTED  ::::::::::::::::: ");

        // We create an array list for simulate reals insertions of history
        List<ExternalPercentageEntity> externalPercentageEntityList=new ArrayList<>();

        // We populate the array list
        externalPercentageEntityList.add(ExternalPercentageEntity.builder().numberOne(5.0).numberTwo(5.0).percentageApplied(10.0).calculatedValue(1.0).dateTime(LocalDateTime.now()).build());
        externalPercentageEntityList.add(ExternalPercentageEntity.builder().numberOne(10.0).numberTwo(10.0).percentageApplied(10.0).calculatedValue(2.0).dateTime(LocalDateTime.now()).build());
        externalPercentageEntityList.add(ExternalPercentageEntity.builder().numberOne(20.0).numberTwo(2.0).percentageApplied(20.0).calculatedValue(4.0).dateTime(LocalDateTime.now()).build());

        // Assertion not null verify that all insert be executed
        externalPercentageEntityList.forEach(x-> Assertions.assertNotNull(iExternalPercentageRepository.save(x)));

        System.out.println(":::::::::::::::::  STORE PERCENTAGE STARTED  ::::::::::::::::: ");
    }

    @Test
    @Order(6)
    @DisplayName("MODIFY PERCENTAGE")
    public void modify_percentage() throws TenpoException {

        System.out.println(":::::::::::::::::  MODIFY PERCENTAGE STARTED  ::::::::::::::::: ");

        // We look for record 2 stored on db
        ExternalPercentageEntity externalPercentageEntityOriginal=iExternalPercentageRepository.findAll().iterator().next();

        // We verify that externalPercentageEntityOriginal is not empty
        Assertions.assertNotNull(externalPercentageEntityOriginal);


        // We create object that will modify record 2
        ExternalPercentageEntity externalPercentageEntityToModify=ExternalPercentageEntity.builder().id(externalPercentageEntityOriginal.getId()).numberOne(30.0).numberTwo(externalPercentageEntityOriginal.getNumberTwo()).percentageApplied(externalPercentageEntityOriginal.getPercentageApplied()).calculatedValue(externalPercentageEntityOriginal.getCalculatedValue()).dateTime(externalPercentageEntityOriginal.getDateTime()).build();

        // We modify record from db
        ExternalPercentageEntity externalPercentageEntityModified = iExternalPercentageRepository.save(externalPercentageEntityToModify);

        // We verify that record list be not null
        Assertions.assertNotEquals(externalPercentageEntityOriginal, externalPercentageEntityModified);

        //We are print original record
        System.out.println("PERCENTAGE Item Original: "+externalPercentageEntityOriginal);

        //We are print original record
        System.out.println("PERCENTAGE Item Modified: "+externalPercentageEntityModified);

        System.out.println(":::::::::::::::::  MODIFY PERCENTAGE ENDED  ::::::::::::::::: ");
    }

    @Test
    @Order(7)
    @DisplayName("GET ALL PERCENTAGE")
    public void get_all_percentage() throws TenpoException {

        System.out.println(":::::::::::::::::  GET ALL PERCENTAGE STARTED  ::::::::::::::::: ");

        // We get all records from db
        Iterable<ExternalPercentageEntity> externalPercentageEntityList = iExternalPercentageRepository.findAll();

        // We verify that record list be not null
        Assertions.assertNotNull(externalPercentageEntityList);

        //We are printing records
        externalPercentageEntityList.forEach(x->System.out.println("PERCENTAGE Item: "+x));

        System.out.println(":::::::::::::::::  GET ALL PERCENTAGE ENDED  ::::::::::::::::: ");
    }

    @Test
    @Order(8)
    @DisplayName("DELETE PERCENTAGE")
    public void delete_percentage() throws TenpoException {

        System.out.println(":::::::::::::::::  DELETE PERCENTAGE STARTED  ::::::::::::::::: ");

        // We define object to delete
        ExternalPercentageEntity externalPercentageEntityToDelete=iExternalPercentageRepository.findAll().iterator().next();

        // We print record to delete from db
        System.out.println("Record to delete: "+externalPercentageEntityToDelete);

        // We delete record from db
        iExternalPercentageRepository.delete(externalPercentageEntityToDelete);

        // We get all records from db
        Iterable<ExternalPercentageEntity> externalPercentageEntityList = iExternalPercentageRepository.findAll();

        // We verify that record list be not null
        Assertions.assertNotNull(externalPercentageEntityList);

        //We are printing records
        externalPercentageEntityList.forEach(x->System.out.println("PERCENTAGE Item: "+x));

        System.out.println(":::::::::::::::::  DELETE PERCENTAGE ENDED  ::::::::::::::::: ");
    }

}