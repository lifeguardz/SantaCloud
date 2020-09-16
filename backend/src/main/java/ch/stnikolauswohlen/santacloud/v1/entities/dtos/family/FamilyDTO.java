package ch.stnikolauswohlen.santacloud.v1.entities.dtos.family;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FamilyDTO
{
    private Long id;
    private String status;
    private boolean special;
    private String firstname;
    private String lastname;
    private String street;
    private int postcode;
    private String city;
    private String phonenumbers;
    private String email;
    private boolean emailnotification;
    private boolean emailnews;
    private int families;
    private int children;
    private String availabledays;
    private String finalDay;
    private String descriptionoffice;
    private String descriptionteam;
    private int teamId;
}
