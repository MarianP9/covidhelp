package ro.scoalainformala.covidhelp.webapp.domain;

import javax.persistence.*;


@Entity
@Table
public class RequestType {

    @Id
    @GeneratedValue
    @Column
    private long id;

    @Column(nullable = false, unique = true)
    private String typeName;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String icon;
}
