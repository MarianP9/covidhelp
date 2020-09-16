package ro.scoalainformala.covidhelp.webapp.service;

import ro.scoalainformala.covidhelp.webapp.domain.Request;
import ro.scoalainformala.covidhelp.webapp.dto.RequestBrowseDto;

import java.util.List;

public interface RequestService {
    int getApprovedRequestsCount();
    int getCompletedRequestsCount();

    List<RequestBrowseDto> getAvailableRequests();
    List<RequestBrowseDto> getFilteredAvailableRequests(String county, String city, String type);

    void addVolunteerToRequest(long requestId, long volunteerId);

    void addRequest(Request request);

    int getShoppingRequestsCount();

    int getPetwalkingRequestsCount();
}
