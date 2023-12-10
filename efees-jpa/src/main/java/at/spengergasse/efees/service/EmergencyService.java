package at.spengergasse.efees.service;

import at.spengergasse.efees.model.Emergency;
import at.spengergasse.efees.repository.EmergencyRepository;
import at.spengergasse.efees.repository.PersonRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor(onConstructor_ = @Autowired, access = AccessLevel.PROTECTED)
public class EmergencyService {

    private final EmergencyRepository emergencyRepository;
    private final PersonRepository personRepository;

    public Optional<Emergency> getOpenEmergency() {
        return emergencyRepository.findOpenEmergencies().stream().findAny();
    }

    public boolean getEmergencyStatus() {
        return getOpenEmergency().isPresent();
    }

    public Emergency saveEmergency(Emergency emergency) {
        return Optional.ofNullable(emergency)
                .map(emergencyRepository::save)
                .orElse(null);
    }

    /*public Optional<Emergency> startEmergency() {
        if (getOpenEmergency().isPresent()) {
            throw new NotValidException("Emergency has already started!");
        }
        return Optional.of(createEmergency());
    }

    public Optional<Emergency> endEmergency() {
        var emergency = getOpenEmergency();
        if (emergency.isEmpty()) {
            throw new NotValidException("No emergency right now!");
        }
        emergency.get().setEndTime(LocalDateTime.now());
        emergency.get().setFinished(true);
        return Optional.of(emergencyRepository.save(emergency.get()));
    }

    public boolean processScanQR(Person person) {
        return changePersonStatus(person, Safety.SAFE);
    }

    public boolean endangered(Person person) {
        return changePersonStatus(person, Safety.IN_DANGER);
    }

    public boolean changePersonStatus(Person person, Safety safety) {
        if (person == null || person.getId() == null) {
            return false;
        }
        return changePersonStatus(person.getEmail(), safety);
    }

    public boolean changePersonStatus(String email, Safety safety) {
        return getOpenEmergency()
                .map(Emergency::getId)
                .map(id -> safePersonRepository.findByEmergency(id, email))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(safePerson -> {
                    safePerson.setSafety(safety);
                    return safePerson;
                })
                .map(safePersonRepository::save)
                .isPresent();
    }

    public List<Person> findAllPersonSafe() {
        return getOpenEmergency()
                .map(Emergency::getId)
                .map(id -> safePersonRepository.findAllBySafety(id, Safety.SAFE))
                .orElse(Collections.emptyList());
    }

    public List<Person> findAllPersonMissing() {
        return getOpenEmergency()
                .map(Emergency::getId)
                .map(id -> safePersonRepository.findAllBySafety(id, Safety.PENDING))
                .orElse(Collections.emptyList());
    }

    public List<Person> findAllPersonInDanger() {
        return getOpenEmergency()
                .map(Emergency::getId)
                .map(id -> safePersonRepository.findAllBySafety(id, Safety.IN_DANGER))
                .orElse(Collections.emptyList());
    }

    public List<StatusDto> findAllPersonWithStatus() {
        List<StatusDto> result = new ArrayList<>();
        getOpenEmergency().map(Emergency::getId)
                .ifPresent(id -> {
                    safePersonRepository.findAllBySafety(id, Safety.SAFE)
                            .forEach(person -> result.add(StatusDto.transformDto(person, Safety.SAFE)));
                    safePersonRepository.findAllBySafety(id, Safety.PENDING)
                            .forEach(person -> result.add(StatusDto.transformDto(person, Safety.PENDING)));
                    safePersonRepository.findAllBySafety(id, Safety.IN_DANGER)
                            .forEach(person -> result.add(StatusDto.transformDto(person, Safety.IN_DANGER)));
                });
        return result;
    }

    public void addWarnSignal(Person person, String signal) {
        if (person == null || person.getId() == null) {
            return;
        }
        addWarnSignal(person.getEmail(), signal);
    }

    public void addWarnSignal(String email, String signal) {
        getOpenEmergency()
                .map(Emergency::getId)
                .map(id -> safePersonRepository.findByEmergency(id, email))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(safePerson -> {
                    safePerson.getSignals().add(signal);
                    return safePerson;
                })
                .map(safePersonRepository::save);
    }

    public Set<String> findWarnSignals(String email) {
        return getOpenEmergency()
                .map(Emergency::getId)
                .map(id -> safePersonRepository.findByEmergency(id, email))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(SafePerson::getSignals)
                .orElse(Set.of());
    }

    private Emergency createEmergency() {
        Emergency emergency = emergencyRepository.save(Emergency.builder().build());
        personRepository.findAll().stream()
                .map(p -> SafePerson.builder().person(p).emergency(emergency).build())
                .forEach(safePersonRepository::save);
        return emergency;
    }*/

}
