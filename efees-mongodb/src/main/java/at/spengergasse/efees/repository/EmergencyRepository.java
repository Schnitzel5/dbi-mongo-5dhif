package at.spengergasse.efees.repository;

import at.spengergasse.efees.dto.StatusDto;
import at.spengergasse.efees.model.Emergency;
import at.spengergasse.efees.model.Person;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EmergencyRepository extends MongoRepository<Emergency, ObjectId> {
    @Aggregation(pipeline = {
            "{ $unwind: '$persons' }",
            "{ $project: { " +
                    "'id': 0, " +
                    "'firstName': '$persons.firstName', " +
                    "'lastName': '$persons.lastName', " +
                    "'email': '$persons.email', " +
                    "'phoneNr': '$persons.phoneNr', " +
                    "'safety': '$persons.safety' } }"
    })
    List<Person> findAllPersons();
    @Aggregation(pipeline = {
            "{ $unwind: '$persons' }",
            "{ $project: { " +
                    "'id': 0, " +
                    "'firstName': '$persons.firstName', " +
                    "'lastName': '$persons.lastName', " +
                    "'email': '$persons.email', " +
                    "'phoneNr': '$persons.phoneNr', " +
                    "'safety': '$persons.safety' } }",
            "{ $match: { 'email': '?0' } }"
    })
    Optional<Person> findByEmail(@Param("email") String email);
    @Aggregation(pipeline = {
            "{ $match: { _id: '?0' } }",
            "{ $unwind: '$persons' }",
            "{ $project: { " +
                    "_id: 0, " +
                    "'firstName': '$persons.firstName', " +
                    "'lastName': '$persons.lastName', " +
                    "'safety': '$persons.safety' } }"
    })
    List<StatusDto> findAllByEmergencyOnlyCrucialInfo(@Param("id") ObjectId id);
    @Aggregation(pipeline = {
            "{ $match: { _id: '?0' } }",
            "{ $unwind: '$persons' }",
            "{ $project: { " +
                    "_id: 0, " +
                    "'firstName': '$persons.firstName', " +
                    "'lastName': '$persons.lastName', " +
                    "'safety': '$persons.safety' } }",
            "{ $sort: { 'safety': 1, 'lastName': 1 } }"
    })
    List<StatusDto> findAllByEmergencyOnlyCrucialInfoSorted(@Param("id") ObjectId id);
    @Query("{ 'persons.email': '?0' }")
    @Update("{ $set: { " +
                    "'persons.$.firstName': '?1', " +
                    "'persons.$.lastName': '?2', " +
                    "'persons.$.email': '?3', " +
                    "'persons.$.phoneNr': '?4', " +
                    "'persons.$.safety': '?5' } }")
    void updatePerson(@Param("email") String email,
                  @Param("firstName") String firstName,
                  @Param("lastName") String lastName,
                  @Param("newEmail") String newEmail,
                  @Param("phoneNr") String phoneNr,
                  @Param("safety") String safety);
}
