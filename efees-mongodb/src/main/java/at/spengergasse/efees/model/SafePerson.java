package at.spengergasse.efees.model;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.HashSet;
import java.util.Set;

@Document("safePersons")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
@ToString
public class SafePerson {
    @MongoId(FieldType.OBJECT_ID)
    protected ObjectId id;
    @DBRef
    private Emergency emergency;
    @DBRef
    private Person person;
    @Setter
    @Builder.Default
    private Safety safety = Safety.PENDING;
    private final Set<String> signals = new HashSet<>();
    @Setter
    private boolean checkedIn;
}