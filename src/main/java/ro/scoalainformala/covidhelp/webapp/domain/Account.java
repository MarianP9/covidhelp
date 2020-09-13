package ro.scoalainformala.covidhelp.webapp.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import ro.scoalainformala.covidhelp.webapp.validation.BirthDate;
import ro.scoalainformala.covidhelp.webapp.validation.Email;
import ro.scoalainformala.covidhelp.webapp.validation.Password;
import ro.scoalainformala.covidhelp.webapp.validation.PhoneNumber;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@Entity
@Table
@Getter
@Setter
public class Account {

    @Id
    @GeneratedValue
    @Column
    private long id;

    @Column(nullable = false, unique = true)
    @Email
    private String email;

    @Column(nullable = false)
    @Password
    private String password;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    @NotNull
    private Role role;

    @Column(nullable = false)
    @NotBlank
    private String lastName;

    @Column(nullable = false)
    @NotBlank
    private String firstName;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @BirthDate
    private LocalDate birthDate;

    @Column(nullable = false)
    @NotBlank
    private String county;

    @Column(nullable = false)
    @NotBlank
    private String city;

    @Column(nullable = false)
    @NotBlank
    private String street;

    @Column
    @NotBlank
    private String address;

    @Column(nullable = false, unique = true)
    @PhoneNumber
    private String phoneNumber;

    @Column
    @NotBlank
    private String profileImage;

    @Column
    private Integer completedRequest;
}
