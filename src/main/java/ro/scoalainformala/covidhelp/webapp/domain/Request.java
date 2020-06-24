package ro.scoalainformala.covidhelp.webapp.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;


@Entity
@Table
public class Request {

    @Id
    @GeneratedValue
    @Column
    private long id;

    @ManyToOne(optional = false)
    private Account requester;

    @ManyToOne(optional = false)
    private RequestType type;

    @Column(nullable = false)
    private String details;

    @Column(nullable = false)
    private LocalDateTime startDate;

    @Column(nullable = false)
    private LocalDateTime endDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column
    private String rejectionReason;

    @OneToMany
    private Set<Account> volunteers;

    @ManyToOne
    private Account completedVolunteer;
}
