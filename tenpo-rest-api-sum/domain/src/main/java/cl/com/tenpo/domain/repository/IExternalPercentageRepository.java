package cl.com.tenpo.domain.repository;

import cl.com.tenpo.domain.entity.ExternalPercentageEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IExternalPercentageRepository extends CrudRepository<ExternalPercentageEntity, String> {
}
