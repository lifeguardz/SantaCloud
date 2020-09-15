package ch.stnikolauswohlen.santacloud.v1.entities.dao.family;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "family")
@Getter
@Setter
public class FamilyDAO
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column
    private String status;

    @Column
    private boolean special;

    @Column
    private String firstname;

    @Column
    private String lastname;

    @Column
    private String street;

    @Column
    private int postcode;

    @Column
    private String city;

    @Column(columnDefinition = "TEXT")
    private String phonenumbers;

    @Column
    private String email;

    @Column
    private boolean emailnotification;

    @Column
    private boolean emailnews;

    @Column
    private int families;

    @Column
    private int children;

    @Column
    private String availabledays;

    @Column(name = "final_day")
    private String finalDay;

    @Column(columnDefinition = "TEXT")
    private String descriptionoffice;

    @Column(columnDefinition = "TEXT")
    private String descriptionteam;

    @Column
    private int teamid;
}
