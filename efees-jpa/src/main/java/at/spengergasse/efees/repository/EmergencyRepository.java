package at.spengergasse.efees.repository;

import at.spengergasse.efees.model.Emergency;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmergencyRepository extends CrudRepository<Emergency, Long> {
    @Query(value = "SELECT e FROM Emergency e WHERE e.finished = false")
    List<Emergency> findOpenEmergencies();
    List<Emergency> findAll();
    Optional<Emergency> findByNotice(String notice);
}
