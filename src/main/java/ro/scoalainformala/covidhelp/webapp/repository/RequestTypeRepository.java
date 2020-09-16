package ro.scoalainformala.covidhelp.webapp.repository;

import org.springframework.data.repository.CrudRepository;
import ro.scoalainformala.covidhelp.webapp.domain.RequestType;

public interface RequestTypeRepository extends CrudRepository<RequestType, Long> {
    RequestType findByTypeName(String name);
}
