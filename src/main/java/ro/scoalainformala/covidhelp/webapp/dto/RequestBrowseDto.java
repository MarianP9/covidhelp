package ro.scoalainformala.covidhelp.webapp.dto;

import lombok.Getter;
import lombok.Setter;
import ro.scoalainformala.covidhelp.webapp.domain.Account;
import ro.scoalainformala.covidhelp.webapp.domain.RequestType;
import ro.scoalainformala.covidhelp.webapp.domain.Status;

import java.time.LocalDateTime;

@Getter
@Setter
public class RequestBrowseDto {
    private long id;
    private Account requester;
    private RequestType type;
    private String details;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Status status;

    public String getName(){
        return requester.getFirstName() + " " + requester.getLastName();
    }

    public String getTypeName(){
        return type.getTypeName();
    }

    public String getLocation() {
        return requester.getCounty() + ", " + requester.getCity();
    }
}
