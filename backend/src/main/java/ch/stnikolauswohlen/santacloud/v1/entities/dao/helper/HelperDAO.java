package ch.stnikolauswohlen.santacloud.v1.entities.dao.helper;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "helper")
@Getter
@Setter
public class HelperDAO
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column
    private String firstname;

    @Column
    private String lastname;

    @Column
    private String email;

    @Column
    private boolean emailnotification;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column
    private Timestamp creationdate;

    /*
     * Rolle
     * 0: Admin
     * 1: Büro
     * 2: Chlaus
     * 3: OS
     * 4: OOS
     * 5: Schmutzli
     * 6: Diener
     */
    @Column(columnDefinition = "SMALLINT")
    private int role;

    @Column(columnDefinition = "SMALLINT")
    private int status;

    @Column
    private String street;

    @Column
    private int postcode;

    @Column
    private String city;

    @Column
    private int birthyear;

    @Column(columnDefinition = "TEXT")
    private String phonenumbers;

    @Column
    private int fieldingdays;
}
