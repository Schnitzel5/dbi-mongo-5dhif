package at.spengergasse.efees.service;

import at.spengergasse.efees.dto.StatusDto;
import at.spengergasse.efees.model.Emergency;
import at.spengergasse.efees.model.Person;
import at.spengergasse.efees.repository.EmergencyRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Transactional
public class EmergencyService {
    private final EmergencyRepository emergencyRepository;

    public List<Emergency> findAll() {
        return emergencyRepository.findAll();
    }

    public List<Person> findAllPersons() {
        return emergencyRepository.findAllPersons();
    }

    public Optional<Person> findByEmail(String email) {
        return emergencyRepository.findByEmail(email);
    }

    public List<StatusDto> findAllByEmergencyOnlyCrucialInfo(ObjectId id) {
        return emergencyRepository.findAllByEmergencyOnlyCrucialInfo(id);
    }

    public List<StatusDto> findAllByEmergencyOnlyCrucialInfoSorted(ObjectId id) {
        return emergencyRepository.findAllByEmergencyOnlyCrucialInfoSorted(id);
    }

    public void updatePerson(String email,
                             String firstName,
                             String lastName,
                             String newEmail,
                             String phoneNr,
                             String safety) {
        emergencyRepository.updatePerson(email, firstName, lastName, newEmail, phoneNr, safety);
    }

    public void deleteAll() {
        emergencyRepository.deleteAll();
    }

    public Emergency saveEmergency(Emergency emergency) {
        return Optional.ofNullable(emergency)
                .map(emergencyRepository::save)
                .orElse(null);
    }
}
