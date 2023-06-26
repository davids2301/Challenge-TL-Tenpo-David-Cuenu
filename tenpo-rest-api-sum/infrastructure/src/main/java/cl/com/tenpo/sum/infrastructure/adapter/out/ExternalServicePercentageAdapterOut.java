package cl.com.tenpo.sum.infrastructure.adapter.out;

import cl.com.tenpo.application.port.out.IExternalServicePercentagePortOut;
import cl.com.tenpo.domain.client.IExternalServicePercentageClient;
import cl.com.tenpo.domain.configuration.annotations.AdapterOut;
import cl.com.tenpo.domain.configuration.exception.TenpoException;
import cl.com.tenpo.domain.dto.ExternalPercentageDTO;
import cl.com.tenpo.domain.dto.ResponseDTO;
import cl.com.tenpo.domain.entity.ExternalPercentageEntity;
import cl.com.tenpo.domain.mapper.IExternalPercentageMapper;
import cl.com.tenpo.domain.repository.IExternalPercentageRepository;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service
@AdapterOut
public class ExternalServicePercentageAdapterOut implements IExternalServicePercentagePortOut {
    
    private final Logger logger= LoggerFactory.logger(ExternalServicePercentageAdapterOut.class);
    
    @Autowired
    private IExternalServicePercentageClient iExternalServicePercentageClient;

    @Value(value = "${message.problems.there.are.not.percentage.cache.stored}")
    private String messageProblemsThereAreNotPercentageCacheStored;

    @Autowired
    private IExternalPercentageRepository iExternalPercentageRepository;

    @Autowired
    private IExternalPercentageMapper iExternalPercentageMapper;

    @Override
    public ExternalPercentageDTO sumTwoNumbersPlusExternalPercentage(Double numberOne, Double numberTwo) throws TenpoException {
        ResponseEntity<ResponseDTO<ExternalPercentageDTO>> externalPercentageResponse=null;
        ExternalPercentageDTO externalPercentageDTO=null;
        try {
            externalPercentageResponse=iExternalServicePercentageClient.sumTwoNumbersAndAndReturnValueApplyingPercentageParametrized(numberOne, numberTwo);
            if(externalPercentageResponse.getStatusCode().is2xxSuccessful()&&!Objects.isNull(externalPercentageResponse.getBody())){
                externalPercentageDTO=externalPercentageResponse.getBody().getData();

                logger.warn("We going to store got value from external service in redis cache");
                iExternalPercentageRepository.save(iExternalPercentageMapper.DTOInEntity(externalPercentageDTO));

                logger.warn("We printed external service value to generate traceability about that it's going: "+externalPercentageDTO);
                return externalPercentageDTO;
            }else {
                logger.warn("External service had answered success but response bring not body, we try get value from redis cache");
                return getLatestPercentageFromCache();
            }
        }catch(Exception ex){
            logger.warn("We have problems trying to get connection with external service, we try get value from redis cache");
            return getLatestPercentageFromCache();
        }
    }

    public ExternalPercentageDTO getLatestPercentageFromCache() throws TenpoException{
        Iterable<ExternalPercentageEntity> percentages= iExternalPercentageRepository.findAll();

        ExternalPercentageEntity percentage=null;

        for(ExternalPercentageEntity p: percentages)
            percentage=p;

        if(!Objects.isNull(percentage)) {
            logger.warn("Final answer to percentage calculate was generated from last value stored in redis cache , Not from external service as should be");
            return iExternalPercentageMapper.EntityInDTO(percentage);
        }

        throw new TenpoException(ExternalServicePercentageAdapterOut.class, messageProblemsThereAreNotPercentageCacheStored);
    }
}
