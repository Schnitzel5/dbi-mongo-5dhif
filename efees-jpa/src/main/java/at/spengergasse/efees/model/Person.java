package at.spengergasse.efees.model;

import at.spengergasse.efees.dto.PersonDto;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
@ToString
public class Person extends AbstractPersistable<Long> {
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
    @ElementCollection
    private final Set<String> signals = new HashSet<>();
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
