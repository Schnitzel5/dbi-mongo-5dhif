package at.spengergasse.efees.model;

import at.spengergasse.efees.dto.PersonDto;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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
    protected String firstName;
    @NotNull
    @Setter
    protected String lastName;
    @NotNull
    @Setter
    protected String email;
    @Setter
    protected String phoneNr;
    @Setter
    private String password;
    @Setter
    @Builder.Default
    private Safety safety = Safety.PENDING;
    @Setter
    private Set<String> signals = new HashSet<>();
    public PersonDto prepareDto() {
        var dto = new PersonDto();
        dto.setId(Optional.ofNullable(getId()).map(String::valueOf).orElse(""));
        dto.setFirstName(getFirstName());
        dto.setLastName(getLastName());
        dto.setEmail(getEmail());
        dto.setPhoneNr(getPhoneNr());
        return dto;
    }
}
