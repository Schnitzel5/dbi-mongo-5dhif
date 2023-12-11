package at.spengergasse.efees.model;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document("persons")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
@ToString
public class Person {
    @MongoId(FieldType.OBJECT_ID)
    protected ObjectId id;
    @NotNull
    @Setter
    @Field("firstName")
    protected String firstName;
    @NotNull
    @Setter
    @Field("lastName")
    protected String lastName;
    @NotNull
    @Setter
    @Field("email")
    protected String email;
    @Setter
    @Field("phoneNr")
    protected String phoneNr;
    @Setter
    @Builder.Default
    @Field("safety")
    private Safety safety = Safety.PENDING;
}
