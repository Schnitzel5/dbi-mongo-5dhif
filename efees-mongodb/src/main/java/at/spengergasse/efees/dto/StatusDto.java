package at.spengergasse.efees.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StatusDto {
    private String firstName;
    private String lastName;
    private String safety;
}
