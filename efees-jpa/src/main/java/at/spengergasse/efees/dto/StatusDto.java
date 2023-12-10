package at.spengergasse.efees.dto;

import at.spengergasse.efees.model.Safety;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class StatusDto {
    private String firstName;
    private String lastName;
    private Safety safety;
}
