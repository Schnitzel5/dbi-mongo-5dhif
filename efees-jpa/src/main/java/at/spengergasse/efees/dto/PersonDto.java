package at.spengergasse.efees.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
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
    private String id = "";
}
