package at.spengergasse.efees.config;

import at.spengergasse.efees.model.*;
import at.spengergasse.efees.service.EmergencyService;
import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired, access = AccessLevel.PROTECTED)
public class PrepareConfig {
    private final EmergencyService emergencyService;

    @EventListener(ApplicationReadyEvent.class)
    public void fillData() {
        insertSample();
    }

    private void insertSample() {
        List<Person> persons = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            Faker faker = new Faker(Locale.GERMAN);
            FakeValuesService fakeValuesService = new FakeValuesService(Locale.GERMAN, new RandomService());
            String email = "prefill" + fakeValuesService.bothify("???#??????##???##??##??##??##@gmail.com");
            String phoneNr = fakeValuesService.regexify("+43 664 \\d{9,15}");
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            var person = Person.builder()
                    .firstName(firstName)
                    .lastName(lastName)
                    .email(email)
                    .phoneNr(phoneNr)
                    .build();
            persons.add(person);
        }
        Emergency emergency = Emergency.builder()
                .persons(persons)
                .build();
        emergencyService.saveEmergency(emergency);
    }
}
