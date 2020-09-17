package ro.scoalainformala.covidhelp.webapp.service.impl;

import org.springframework.stereotype.Service;
import ro.scoalainformala.covidhelp.webapp.domain.Account;
import ro.scoalainformala.covidhelp.webapp.domain.Request;
import ro.scoalainformala.covidhelp.webapp.domain.Status;
import ro.scoalainformala.covidhelp.webapp.dto.RequestBrowseDto;
import ro.scoalainformala.covidhelp.webapp.repository.RequestRepository;
import ro.scoalainformala.covidhelp.webapp.repository.RequestTypeRepository;
import ro.scoalainformala.covidhelp.webapp.service.AccountService;
import ro.scoalainformala.covidhelp.webapp.service.RequestService;
import ro.scoalainformala.covidhelp.webapp.transformer.RequestToRequestBrowseDtoTransformer;

import java.util.ArrayList;
import java.util.List;

@Service
public class RequestServiceImpl implements RequestService {

    private final RequestRepository repository;
    private final RequestTypeRepository requestTypeRepository;
    private final RequestToRequestBrowseDtoTransformer transformer;
    private final AccountService accountService;

    public RequestServiceImpl(RequestRepository repository, RequestTypeRepository requestTypeRepository, RequestToRequestBrowseDtoTransformer transformer, AccountService accountService) {
        this.repository = repository;
        this.requestTypeRepository = requestTypeRepository;
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
    public List<RequestBrowseDto> getFilteredAvailableRequests(String county, String city, String type) {
        List<RequestBrowseDto> requestDtoList = new ArrayList<>();
        List<Request> requestList;
        if(county.isEmpty() && city.isEmpty() && type.isEmpty())
            requestList = repository.getRequestsByStatus(Status.APPROVED);
        else if(county.isEmpty() && city.isEmpty())
            requestList = repository.getRequestByStatusAndType(Status.APPROVED, requestTypeRepository.findByTypeName(type));
        else if(county.isEmpty() && type.isEmpty())
            requestList = repository.getRequestByStatusAndRequesterCity(Status.APPROVED, city);
        else if(city.isEmpty() && type.isEmpty())
            requestList = repository.getRequestByStatusAndRequesterCounty(Status.APPROVED, county);
        else if(county.isEmpty())
            requestList = repository.getRequestByStatusAndRequesterCityAndType(Status.APPROVED, city, requestTypeRepository.findByTypeName(type));
        else if(city.isEmpty())
            requestList = repository.getRequestByStatusAndRequesterCountyAndType(Status.APPROVED, county, requestTypeRepository.findByTypeName(type));
        else if(type.isEmpty())
            requestList = repository.getRequestByStatusAndRequesterCountyAndRequesterCity(Status.APPROVED, county, city);
        else
            requestList = repository.getRequestByStatusAndRequesterCountyAndRequesterCityAndType(Status.APPROVED, county, city, requestTypeRepository.findByTypeName(type));
        requestList.forEach(request -> requestDtoList.add(transformer.transform(request)));
        return requestDtoList;
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

    @Override
    public int getShoppingRequestsCount() {
        return repository.countByStatusAndType(Status.COMPLETED, requestTypeRepository.findByTypeName("Shopping"));
    }

    @Override
    public int getPetwalkingRequestsCount() {
        return repository.countByStatusAndType(Status.COMPLETED, requestTypeRepository.findByTypeName("Petwalking"));
    }
}
