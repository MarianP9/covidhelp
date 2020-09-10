package ro.scoalainformala.covidhelp.webapp.service;

import ro.scoalainformala.covidhelp.webapp.domain.Request;

import java.util.List;

public interface RequesterService {

    String firstName(String email);
    String lastName(String email);
    List<Request> getRequestActive(String email);
    List<Request> getRequestInactive(String email);
    List<Request> getRequestWithVolunteer(String email);
    List<Integer> getRequestWithVolunteerSize(String email);
    void cancelRequest(Long id);
    void completeRequest(Long idr, Long idv);

}
