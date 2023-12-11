package at.spengergasse.efees.presentation;

import at.spengergasse.efees.model.Emergency;
import at.spengergasse.efees.service.EmergencyService;
import at.spengergasse.efees.service.PersonService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequiredArgsConstructor(onConstructor_ = @Autowired, access = AccessLevel.PROTECTED)
public class WebController {
    private final EmergencyService emergencyService;
    private final PersonService personService;

    @GetMapping(path = "/{emergency}")
    public String getEmergency(@PathVariable(required = false) Optional<Long> emergency,
                               Model model) {
        var emergencies = emergencyService.findAll();
        var temp = Optional.ofNullable(emergencies.get(0)).map(Emergency::getId).orElse(-1L);
        if (emergencies.isEmpty() || temp == -1) {
            return "no";
        }
        model.addAttribute("emergencies", emergencies);
        model.addAttribute("persons",
                personService.findAllByEmergencyOnlyCrucialInfoSorted(emergency.orElse(temp)));
        return "index";
    }

}
