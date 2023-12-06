package at.spengergasse.efees.dto;

import at.spengergasse.efees.model.Person;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.bson.types.ObjectId;

@Data
@AllArgsConstructor
public class PersonDto {
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    @Email
    private String email;
    private String phoneNr;
    @Pattern(regexp = "|.{4,128}", message = "Password too short or too long!")
    private String password;
    private ObjectId id;

    public static PersonDto fromEntity(Person p){
        return new PersonDto(
                p.getFirstName(), p.getLastName(),
                p.getEmail(), p.getPhoneNr(),
                p.getPassword(), p.getId());
    }
}
