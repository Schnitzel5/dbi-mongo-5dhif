package at.spengergasse.efees.model;

import jakarta.persistence.Entity;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.time.LocalDateTime;

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
}
