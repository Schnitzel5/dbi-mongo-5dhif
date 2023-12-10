package at.spengergasse.efees.repository;

import at.spengergasse.efees.dto.StatusDto;
import at.spengergasse.efees.model.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {
    Optional<Person> findByEmail(String email);
    List<Person> findAll();
    @Query("select new at.spengergasse.efees.dto.StatusDto(p.firstName, p.lastName, p.safety) " +
            "from Person p " +
            "inner join Emergency e on e = p.emergency " +
            "where e.id = :id")
    List<StatusDto> findAllByEmergencyOnlyCrucialInfo(@Param("id") long id);
    @Query("select new at.spengergasse.efees.dto.StatusDto(p.firstName, p.lastName, p.safety) " +
            "from Person p " +
            "inner join Emergency e on e = p.emergency " +
            "where e.id = :id " +
            "order by p.safety, p.lastName")
    List<StatusDto> findAllByEmergencyOnlyCrucialInfoSorted(@Param("id") long id);
}
