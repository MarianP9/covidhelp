package ro.scoalainformala.covidhelp.webapp.service.impl;

import org.springframework.stereotype.Service;
import ro.scoalainformala.covidhelp.webapp.domain.Status;
import ro.scoalainformala.covidhelp.webapp.repository.RequestRepository;
import ro.scoalainformala.covidhelp.webapp.service.RequestService;

@Service
public class RequestServiceImpl implements RequestService {

    private final RequestRepository repository;

    public RequestServiceImpl(RequestRepository repository) {
        this.repository = repository;
    }


    @Override
    public int getApprovedRequestsCount() {
        return repository.countByStatus(Status.APPROVED);
    }

    @Override
    public int getCompletedRequestsCount() {
        return repository.countByStatus(Status.COMPLETED);
    }
}
