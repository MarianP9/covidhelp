package ro.scoalainformala.covidhelp.webapp.repository;

import org.springframework.data.repository.CrudRepository;
import ro.scoalainformala.covidhelp.webapp.domain.Request;
import ro.scoalainformala.covidhelp.webapp.domain.Status;

public interface RequestRepository extends CrudRepository<Request, Long> {

    int countByStatus(Status status);
}
