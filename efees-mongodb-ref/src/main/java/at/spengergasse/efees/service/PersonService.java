package at.spengergasse.efees.service;

import at.spengergasse.efees.model.Person;
import at.spengergasse.efees.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Transactional
public class PersonService {
    private final PersonRepository personRepository;

    public Optional<Person> findPersonById(String objectId) {
        return Optional.ofNullable(objectId)
                .filter(ObjectId::isValid)
                .map(ObjectId::new)
                .flatMap(personRepository::findById);
    }

    public Optional<Person> findPersonByEmail(String email) {
        return personRepository.findByEmail(email);
    }

    public List<Person> findAllPersons() {
        return personRepository.findAll();
    }

    public Person saveUser(Person user) {
        return Optional.ofNullable(user)
                .map(personRepository::save)
                .orElse(null);
    }

    public void saveUserBatch(List<Person> users) {
        personRepository.saveAll(users);
    }

    public Person updateUser(String email, Person person) {
        if (email == null || person == null) {
            return null;
        }
        var temp = personRepository.findByEmail(email);
        if (temp.isPresent()) {
            Optional.ofNullable(person.getFirstName()).ifPresent(temp.get()::setFirstName);
            Optional.ofNullable(person.getLastName()).ifPresent(temp.get()::setLastName);
            Optional.ofNullable(person.getEmail()).ifPresent(temp.get()::setEmail);
            Optional.ofNullable(person.getPhoneNr()).ifPresent(temp.get()::setPhoneNr);
            Optional.ofNullable(person.getSafety()).ifPresent(temp.get()::setSafety);
            return saveUser(temp.get());
        }
        return null;
    }

    public void deleteUser(String objectId) {
        Optional.ofNullable(objectId)
            .filter(ObjectId::isValid)
            .map(ObjectId::new)
            .ifPresent(personRepository::deleteById);
    }

    public void deleteAll() {
        personRepository.deleteAll();
    }
}
