package ch.stnikolauswohlen.santacloud.v1.entities.dtos.helper;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class HelperListDTO
{
    private int role;
    private String firstname;
    private String lastname;
    private int birthyear;
    private int fieldingdays;
    private String phonenumbers;
}
