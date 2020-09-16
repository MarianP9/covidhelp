package ro.scoalainformala.covidhelp.webapp.repository;

import org.springframework.data.repository.CrudRepository;
import ro.scoalainformala.covidhelp.webapp.domain.Request;
import ro.scoalainformala.covidhelp.webapp.domain.RequestType;
import ro.scoalainformala.covidhelp.webapp.domain.Status;

import javax.validation.constraints.NotBlank;
import java.util.List;

public interface RequestRepository extends CrudRepository<Request, Long> {

    int countByStatus(Status status);
    int countByStatusAndType(Status status, RequestType requestType);

    Request getRequestById(long id);
    List<Request> getRequestsByStatus(Status status);
    List<Request> getRequestByStatusAndRequesterCounty(Status status, String requester_county);
    List<Request> getRequestByStatusAndRequesterCity(Status status, String city);
    List<Request> getRequestByStatusAndType(Status status, RequestType type);
    List<Request> getRequestByStatusAndRequesterCountyAndRequesterCity(Status status, String requester_county, String requester_city);
    List<Request> getRequestByStatusAndRequesterCountyAndType(Status status, String requester_county, RequestType type);
    List<Request> getRequestByStatusAndRequesterCityAndType(Status status, String requester_city, RequestType type);
    List<Request> getRequestByStatusAndRequesterCountyAndRequesterCityAndType(Status status, String requester_county, String requester_city, RequestType type);
}
