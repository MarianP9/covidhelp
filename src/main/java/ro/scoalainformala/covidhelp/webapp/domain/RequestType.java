package ro.scoalainformala.covidhelp.webapp.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString

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
