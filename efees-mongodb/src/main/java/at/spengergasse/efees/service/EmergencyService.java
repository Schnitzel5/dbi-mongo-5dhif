package at.spengergasse.efees.service;

import at.spengergasse.efees.model.Emergency;
import at.spengergasse.efees.repository.EmergencyRepository;
import lombok.RequiredArgsConstructor;
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

    public Emergency saveEmergency(Emergency emergency) {
        return Optional.ofNullable(emergency)
                .map(emergencyRepository::save)
                .orElse(null);
    }
}
