package ro.scoalainformala.covidhelp.webapp.repository;

import org.springframework.data.repository.CrudRepository;
import ro.scoalainformala.covidhelp.webapp.domain.Request;
import ro.scoalainformala.covidhelp.webapp.domain.RequestType;
import ro.scoalainformala.covidhelp.webapp.domain.Status;

import java.util.List;

public interface RequestRepository extends CrudRepository<Request, Long> {

    int countByStatus(Status status);
    int countByStatusAndType(Status status, RequestType requestType);

    Request getRequestById(long id);
    List<Request> getRequestsByStatus(Status status);
    List<Request> getRequestByStatusAndRequesterCounty(Status status, String requester_county);
    List<Request> getRequestByStatusAndAndRequesterCountyAndAndRequesterCity(Status status, String requester_county, String requester_city);
}
