package ro.scoalainformala.covidhelp.webapp.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ro.scoalainformala.covidhelp.webapp.domain.Account;
import ro.scoalainformala.covidhelp.webapp.domain.Request;
import ro.scoalainformala.covidhelp.webapp.domain.Status;
import ro.scoalainformala.covidhelp.webapp.repository.AccountRepository;
import ro.scoalainformala.covidhelp.webapp.repository.RequestRepository;

import java.util.*;

import static java.util.stream.Collectors.toList;

@Service
@Log4j2
public class RequesterServiceImpl implements RequesterService {

    public final AccountRepository accountRepository;
    public final RequestRepository requestRepository;

    public RequesterServiceImpl(AccountRepository accountRepository,
                                RequestRepository requestRepository) {
        this.accountRepository = accountRepository;
        this.requestRepository = requestRepository;
    }

    // with this method you get access to the login information
    public String getEmail() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email;
        if (principal instanceof UserDetails) {
            email = ((UserDetails) principal).getUsername();
        } else {
            email = principal.toString();
        }
        return email;
    }

    @Override
    public String firstName(String email) {
        return Objects.requireNonNull(accountRepository.findByEmail(email).orElse(null)).getFirstName();
    }

    @Override
    public String lastName(String email) {
        return Objects.requireNonNull(accountRepository.findByEmail(email).orElse(null)).getLastName();
    }

    //make a list which will have all the active requests
    @Override
    public List<Request> getRequestActive(String email) {
        long id = Objects.requireNonNull(accountRepository.findByEmail(email).orElse(null)).getId();
        List<Request> requests = new ArrayList<>();
        requestRepository.findAll().forEach(requests::add);
        List<Request> requestFilter = new ArrayList<>();
        requests.stream()
                .filter(f -> f.getRequester().getId() == id)
                .filter(f -> f.getStatus() == Status.PENDING || f.getStatus() == Status.APPROVED)
                .sorted(Comparator.comparing(Request::getId).reversed()).collect(toList())
                .forEach(requestFilter::add);

        return requestFilter;
    }

    //make a list which will have all the inactive requests
    @Override
    public List<Request> getRequestInactive(String email) {
        long id = Objects.requireNonNull(accountRepository.findByEmail(email).orElse(null)).getId();
        List<Request> requests = new ArrayList<>();
        requestRepository.findAll().forEach(requests::add);
        List<Request> requestFilter = new ArrayList<>();
        requests.stream()
                .filter(f -> f.getRequester().getId() == id)
                .filter(f -> f.getStatus() == Status.COMPLETED || f.getStatus() == Status.CANCELLED || f.getStatus() == Status.REJECTED)
                .sorted(Comparator.comparing(Request::getId).reversed()).collect(toList())
                .forEach(requestFilter::add);

        return requestFilter;
    }

    @Override
    public List<Request> getRequestWithVolunteer(String email) {
        long id = Objects.requireNonNull(accountRepository.findByEmail(email).orElse(null)).getId();
        List<Request> requests = new ArrayList<>();
        requestRepository.findAll().forEach(requests::add);

        List<Request> requestFilter = new ArrayList<>();
        requests.stream()
                .filter(f -> f.getRequester().getId() == id)
                .filter(f -> f.getStatus() == Status.APPROVED)
                .filter(f -> f.getVolunteers().size() != 0)
                .sorted(Comparator.comparing(Request::getId).reversed()).collect(toList())
                .forEach(requestFilter::add);
        return requestFilter;
    }

    public List<Integer> getRequestWithVolunteerSize(String email) {
        List<Request> requestFilter = getRequestWithVolunteer(email);
        List<Integer> integerList = new ArrayList<>();
        for (Request request : requestFilter) {
            int j = request.getVolunteers().size();
            integerList.add(j);
        }
        return integerList;
    }

    @Override
    public void cancelRequest(@Param("id") Long id) {
        Optional<Request> getEntityById = requestRepository.findById(id);
        Request request = getEntityById.orElse(null);
        Objects.requireNonNull(request).setStatus(Status.CANCELLED);
        requestRepository.save(request);
    }

    @Override
    public void completeRequest(@Param("idr") Long idr, @Param("idv") Long idv) {
        Optional<Request> completed = requestRepository.findById(idr);
        Request request = completed.orElse(null);
        Objects.requireNonNull(request).setStatus(Status.COMPLETED);
        Account completedVolunteer = accountRepository.findById(idv).get();
        Integer completedRequest = completedVolunteer.getCompletedRequest();
        if (completedRequest == null) {
            completedRequest = 0;
        }
        int completedIterate = completedRequest + 1;
        completedVolunteer.setCompletedRequest(completedIterate);
        accountRepository.save(completedVolunteer);
        request.setCompletedVolunteer(completedVolunteer);
        requestRepository.save(request);
    }

}
