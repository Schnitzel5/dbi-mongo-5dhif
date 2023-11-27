package at.spengergasse.efees.repository;

import at.spengergasse.efees.model.Person;
import at.spengergasse.efees.model.SafePerson;
import at.spengergasse.efees.model.Safety;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SafePersonRepository extends CrudRepository<SafePerson, Long> {
    @Query(value = "SELECT sp FROM SafePerson sp " +
            "WHERE sp.emergency.id = :emergency")
    List<SafePerson> findAllByEmergencyOnly(@Param("emergency") long emergencyId);
    @Query(value = "SELECT sp.person FROM SafePerson sp " +
            "WHERE sp.emergency.id = :emergency AND sp.safety = :safety")
    List<Person> findAllBySafety(@Param("emergency") long emergencyId,
                                 @Param("safety") Safety safety);
    @Query(value = "SELECT sp FROM SafePerson sp " +
            "WHERE sp.emergency.id = :emergency AND sp.person.email = :email")
    Optional<SafePerson> findByEmergency(@Param("emergency") long emergencyId,
                                         @Param("email") String email);
}
