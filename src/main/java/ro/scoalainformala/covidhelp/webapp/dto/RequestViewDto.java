package ro.scoalainformala.covidhelp.webapp.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ro.scoalainformala.covidhelp.webapp.domain.Account;
import ro.scoalainformala.covidhelp.webapp.domain.Status;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@ToString
public class RequestViewDto extends RequestBaseDto {

    private Long requestId;
    private Long requesterId;
    private String requestType;
    private String details;
    private String address;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Status status;
    private String rejectionReason;
    private Set<Account> volunteers;

}
