package at.spengergasse.efees.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
@ToString
public class SafePerson extends AbstractPersistable<Long> {
    @ManyToOne(optional = false, cascade = {CascadeType.REFRESH})
    private Emergency emergency;
    @ManyToOne(optional = false, cascade = {CascadeType.REFRESH})
    private Person person;
    @Setter
    @Builder.Default
    private Safety safety = Safety.PENDING;
    @ElementCollection
    private final Set<String> signals = new HashSet<>();
    @Setter
    private boolean checkedIn;
}
