package ro.scoalainformala.covidhelp.webapp.service;

import ro.scoalainformala.covidhelp.webapp.domain.RequestType;

import java.util.List;

public interface RequestTypeService {
    List<RequestType> getAll();
}
