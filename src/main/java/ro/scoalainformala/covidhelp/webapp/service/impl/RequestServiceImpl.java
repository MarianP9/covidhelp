package ro.scoalainformala.covidhelp.webapp.service.impl;

import org.springframework.stereotype.Service;
import ro.scoalainformala.covidhelp.webapp.domain.Account;
import ro.scoalainformala.covidhelp.webapp.domain.Request;
import ro.scoalainformala.covidhelp.webapp.domain.Status;
import ro.scoalainformala.covidhelp.webapp.dto.RequestBrowseDto;
import ro.scoalainformala.covidhelp.webapp.repository.RequestRepository;
import ro.scoalainformala.covidhelp.webapp.service.AccountService;
import ro.scoalainformala.covidhelp.webapp.service.RequestService;
import ro.scoalainformala.covidhelp.webapp.transformer.RequestToRequestBrowseDtoTransformer;

import java.util.ArrayList;
import java.util.List;

@Service
public class RequestServiceImpl implements RequestService {

    private final RequestRepository repository;
    private final RequestToRequestBrowseDtoTransformer transformer;
    private final AccountService accountService;

    public RequestServiceImpl(RequestRepository repository, RequestToRequestBrowseDtoTransformer transformer, AccountService accountService) {
        this.repository = repository;
        this.accountService = accountService;
        this.transformer = transformer;
    }


    @Override
    public int getApprovedRequestsCount() {
        return repository.countByStatus(Status.APPROVED);
    }

    @Override
    public int getCompletedRequestsCount() {
        return repository.countByStatus(Status.COMPLETED);
    }

    @Override
    public List<RequestBrowseDto> getAvailableRequests() {
        List<RequestBrowseDto> requestList = new ArrayList<>();
        repository.getRequestsByStatus(Status.APPROVED)
                .forEach(request -> requestList.add(transformer.transform(request)));
        return requestList;
    }

    @Override
    public List<RequestBrowseDto> getAvailableRequestsByCounty(String county) {
        List<RequestBrowseDto> requestList = new ArrayList<>();
        repository.getRequestByStatusAndRequesterCounty(Status.APPROVED, county)
                .forEach(request -> requestList.add(transformer.transform(request)));
        System.out.println("Queried requests for " + county + " , found " + requestList.size() + " results");
        return requestList;
    }

    @Override
    public List<RequestBrowseDto> getAvailableRequestsByCountyAndCity(String county, String city) {
        List<RequestBrowseDto> requestList = new ArrayList<>();
        repository.getRequestByStatusAndAndRequesterCountyAndAndRequesterCity(Status.APPROVED, county, city)
                .forEach(request -> requestList.add(transformer.transform(request)));
        System.out.println("Queried requests for " + county +", "+ city + ", found " + requestList.size() + " results");
        return requestList;

    }

    @Override
    public void addVolunteerToRequest(long requestId, long volunteerId) {
        Account volunteer = accountService.getAccountById(volunteerId);
        Request request = repository.getRequestById(requestId);
        request.getVolunteers().add(volunteer);
        repository.save(request);
    }

    @Override
    public void addRequest(Request request) {
        repository.save(request);
    }
}
