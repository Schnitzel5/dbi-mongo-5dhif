package at.spengergasse.efees.presentation;

import at.spengergasse.efees.model.Emergency;
import at.spengergasse.efees.service.EmergencyService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
@RequiredArgsConstructor(onConstructor_ = @Autowired, access = AccessLevel.PROTECTED)
public class WebController {
    private final EmergencyService emergencyService;

    @GetMapping
    public String redirectDefault() {
        var emergencies = emergencyService.findAll();
        var temp = Optional.ofNullable(emergencies.get(0)).map(Emergency::getId).orElse(null);
        if (temp == null) {
            return "no";
        }
        return "redirect:/" + temp.toHexString();
    }

    @GetMapping(path = "/{emergency}")
    public String getEmergency(@PathVariable(required = false) Optional<String> emergency,
                               Model model) {
        var emergencies = emergencyService.findAll();
        var temp = Optional.ofNullable(emergencies.get(0)).map(Emergency::getId).orElse(null);
        if (temp == null) {
            return "no";
        }
        var objectId = emergency
                .filter(ObjectId::isValid)
                .map(ObjectId::new)
                .orElse(temp);
        model.addAttribute("currentEmergency", objectId);
        model.addAttribute("emergencies", emergencies);
        model.addAttribute("persons",
                emergencyService.findAllByEmergencyOnlyCrucialInfoSorted(objectId));
        return "index";
    }

}
