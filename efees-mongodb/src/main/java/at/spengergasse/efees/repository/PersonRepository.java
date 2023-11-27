package at.spengergasse.efees.repository;

import at.spengergasse.efees.model.Person;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PersonRepository extends MongoRepository<Person, ObjectId> {
}
