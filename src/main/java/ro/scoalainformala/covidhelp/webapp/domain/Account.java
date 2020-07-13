package ro.scoalainformala.covidhelp.webapp.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@ToString

@Entity
@Table
public class Account {

    @Id
    @GeneratedValue
    @Column
    private long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private LocalDate birthDate;

    @Column(nullable = false)
    private String county;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String street;

    @Column
    private String address;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    @Column
    private String profileImage;
}
