package ro.scoalainformala.covidhelp.webapp.service;

import ro.scoalainformala.covidhelp.webapp.domain.Request;
import ro.scoalainformala.covidhelp.webapp.dto.RequestBrowseDto;

import java.util.List;

public interface RequestService {
    int getApprovedRequestsCount();
    int getCompletedRequestsCount();

    List<RequestBrowseDto> getAvailableRequests();
    List<RequestBrowseDto> getAvailableRequestsByCounty(String county);
    List<RequestBrowseDto> getAvailableRequestsByCountyAndCity(String county, String city);

    void addVolunteerToRequest(long requestId, long volunteerId);

    void addRequest(Request request);
}
