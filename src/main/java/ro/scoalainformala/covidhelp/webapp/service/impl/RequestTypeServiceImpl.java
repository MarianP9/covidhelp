package ro.scoalainformala.covidhelp.webapp.service.impl;

import org.springframework.stereotype.Service;
import ro.scoalainformala.covidhelp.webapp.domain.RequestType;
import ro.scoalainformala.covidhelp.webapp.repository.RequestTypeRepository;
import ro.scoalainformala.covidhelp.webapp.service.RequestTypeService;

import java.util.ArrayList;
import java.util.List;

@Service
public class RequestTypeServiceImpl implements RequestTypeService {
    private final RequestTypeRepository repository;

    public RequestTypeServiceImpl(RequestTypeRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<RequestType> getAll() {
        List<RequestType> requestTypes = new ArrayList<>();
        repository.findAll().forEach(requestTypes::add);
        return requestTypes;
    }
}
