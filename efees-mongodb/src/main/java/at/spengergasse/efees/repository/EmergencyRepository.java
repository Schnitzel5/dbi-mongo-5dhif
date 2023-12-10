package at.spengergasse.efees.repository;

import at.spengergasse.efees.model.Emergency;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmergencyRepository extends MongoRepository<Emergency, ObjectId> {
}
