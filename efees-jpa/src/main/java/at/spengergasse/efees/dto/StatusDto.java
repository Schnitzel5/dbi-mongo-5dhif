package at.spengergasse.efees.dto;

import at.spengergasse.efees.model.Person;
import at.spengergasse.efees.model.Safety;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StatusDto {
    private PersonDto person;
    private String status;
    public static StatusDto transformDto(Person person, Safety status) {
        if (person == null || status == null) {
            return null;
        }
        return StatusDto.builder()
                .person(person.prepareDto())
                .status(status.name())
                .build();
    }
}
