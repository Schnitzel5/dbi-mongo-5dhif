package at.spengergasse.efees.config;

import at.spengergasse.efees.model.*;
import at.spengergasse.efees.repository.PersonRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired, access = AccessLevel.PROTECTED)
public class PrepareConfig {
    private final PersonRepository personRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void fillData() {
        var person = Person.builder()
                .firstName("Philip")
                .lastName("Duong")
                .email("DUO20246@spengergasse.at")
                .phoneNr("+43 664 999999")
                .password("root")
                .role(Role.ROLE_STUDENT)
                .enabled(true)
                .build();
        personRepository.save(person);
    }
}
