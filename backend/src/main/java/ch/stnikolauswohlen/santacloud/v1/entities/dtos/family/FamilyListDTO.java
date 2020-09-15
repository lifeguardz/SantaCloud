package ch.stnikolauswohlen.santacloud.v1.entities.dtos.family;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FamilyListDTO
{
    private Long id;
    private String firstname;
    private String lastname;
    private String street;
    private String phonenumbers;
}
