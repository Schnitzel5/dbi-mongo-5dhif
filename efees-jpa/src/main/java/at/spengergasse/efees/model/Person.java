package at.spengergasse.efees.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

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
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private Safety safety = Safety.PENDING;
    @ManyToOne(cascade = CascadeType.MERGE)
    @Setter
    private Emergency emergency;
}
