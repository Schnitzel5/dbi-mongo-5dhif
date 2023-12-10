package at.spengergasse.efees.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
@ToString
public class Emergency extends AbstractPersistable<Long> {
    @Setter
    @Builder.Default
    private LocalDateTime startTime = LocalDateTime.now();
    @Setter
    private LocalDateTime endTime;
    @Setter
    @Builder.Default
    private String notice = "";
    @Setter
    private boolean finished;
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @ToString.Exclude
    private List<Person> persons;
}
