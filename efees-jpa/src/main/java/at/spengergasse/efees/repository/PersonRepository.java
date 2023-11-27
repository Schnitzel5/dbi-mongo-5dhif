package at.spengergasse.efees.repository;

import at.spengergasse.efees.model.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {
    Optional<Person> findByEmail(String email);
    List<Person> findAll();
}
