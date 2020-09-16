package ch.stnikolauswohlen.santacloud.v1.entities.dtos.helper;

import java.sql.Timestamp;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HelperDTO
{
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private boolean emailnotification;
    private String description;
    private Timestamp creationdate;
    private int role;
    private int status;
    private String street;
    private int postcode;
    private String city;
    private int birthyear;
    private String phonenumbers;
    private int fieldingdays;
    private int teamId;
}
