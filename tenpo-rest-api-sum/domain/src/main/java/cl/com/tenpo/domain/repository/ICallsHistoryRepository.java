package cl.com.tenpo.domain.repository;

import cl.com.tenpo.domain.entity.CallsHistoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ICallsHistoryRepository extends JpaRepository<CallsHistoryEntity, Integer> {

    @Query(value = "SELECT c FROM CallsHistoryEntity c")
    Page<CallsHistoryEntity> callsHistoryPaginated(Pageable pageable);

}