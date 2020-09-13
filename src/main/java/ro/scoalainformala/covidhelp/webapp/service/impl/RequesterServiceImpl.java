package ro.scoalainformala.covidhelp.webapp.service.impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ro.scoalainformala.covidhelp.webapp.domain.Account;
import ro.scoalainformala.covidhelp.webapp.domain.Request;
import ro.scoalainformala.covidhelp.webapp.domain.Status;

import ro.scoalainformala.covidhelp.webapp.dto.RequestViewDto;
import ro.scoalainformala.covidhelp.webapp.transformer.RequestToRequestViewDtoTransformer;
import ro.scoalainformala.covidhelp.webapp.repository.AccountRepository;
import ro.scoalainformala.covidhelp.webapp.repository.RequestRepository;
import ro.scoalainformala.covidhelp.webapp.service.RequesterService;

import java.util.*;

import static java.util.stream.Collectors.toList;

@Service
@Log4j2
public class RequesterServiceImpl implements RequesterService {

    public final AccountRepository accountRepository;
    public final RequestRepository requestRepository;

    public final RequestToRequestViewDtoTransformer transformer;

    public RequesterServiceImpl(AccountRepository accountRepository,
                                RequestRepository requestRepository,
                                RequestToRequestViewDtoTransformer transformer) {
        this.accountRepository = accountRepository;
        this.requestRepository = requestRepository;
        this.transformer = transformer;
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

    // this method will get the first name of the user
    @Override
    public String firstName(String email) {
        return Objects.requireNonNull(accountRepository.findByEmail(email).orElse(null)).getFirstName();
    }


    // this method will get the last name of the user
    @Override
    public String lastName(String email) {
        return Objects.requireNonNull(accountRepository.findByEmail(email).orElse(null)).getLastName();
    }

    // this method will transform all request (which is the domain) in request view dto and will add in a list
    @Override
    public List<RequestViewDto> getAllRequestViewDtoList() {
        List<RequestViewDto> requestViewDtoList = new ArrayList<>();
        requestRepository.findAll().forEach(request -> requestViewDtoList.add(transformer.transform(request)));
        return requestViewDtoList;
    }

    //make a list which will have all the active requests
    @Override
    public List<RequestViewDto> getRequestActive(String email) {
        long id = Objects.requireNonNull(accountRepository.findByEmail(email).orElse(null)).getId();
        List<RequestViewDto> requestViewDtoList = getAllRequestViewDtoList();
        List<RequestViewDto> requestFilter = new ArrayList<>();
        test.stream()
                .filter(f -> f.getRequesterId() == id)
                .filter(f -> f.getStatus() == Status.PENDING || f.getStatus() == Status.APPROVED)
                .sorted(Comparator.comparing(RequestViewDto::getRequestId).reversed()).collect(toList())
                .forEach(requestFilter::add);
        log.info(requestFilter);
        return requestFilter;
    }

    //make a list which will have all the inactive requests
    @Override
    public List<RequestViewDto> getRequestInactive(String email) {
        long id = Objects.requireNonNull(accountRepository.findByEmail(email).orElse(null)).getId();
        List<RequestViewDto> requestViewDtoList = getAllRequestViewDtoList();
        List<RequestViewDto> requestFilter = new ArrayList<>();
        test.stream()
                .filter(f -> f.getRequesterId() == id)
                .filter(f -> f.getStatus() == Status.COMPLETED || f.getStatus() == Status.CANCELLED || f.getStatus() == Status.REJECTED)
                .sorted(Comparator.comparing(RequestViewDto::getRequestId).reversed()).collect(toList())
                .forEach(requestFilter::add);

        return requestFilter;
    }


    // this method will get those request view dto which was accepted by a volunteer
    @Override
    public List<RequestViewDto> getRequestWithVolunteer(String email) {
        long id = Objects.requireNonNull(accountRepository.findByEmail(email).orElse(null)).getId();
        List<RequestViewDto> test = getAllRequestViewDtoList();
        List<RequestViewDto> requestFilter = new ArrayList<>();
        test.stream()
                .filter(f -> f.getRequesterId() == id)
                .filter(f -> f.getStatus() == Status.APPROVED)
                .filter(f -> f.getVolunteers().size() != 0)
                .sorted(Comparator.comparing(RequestViewDto::getRequestId).reversed()).collect(toList())
                .forEach(requestFilter::add);
        return requestFilter;
    }

    public List<Integer> getRequestWithVolunteerSize(String email) {
        List<RequestViewDto> requestFilter = getRequestWithVolunteer(email);
        List<Integer> integerList = new ArrayList<>();
        for (RequestViewDto requestViewDto : requestFilter) {
            int j = requestViewDto.getVolunteers().size();
            integerList.add(j);
        }
        return integerList;
    }


    // this method will cancel a request
    @Override
    public void cancelRequest(@Param("id") Long id) {
        Optional<Request> getEntityById = requestRepository.findById(id);
        Request request = getEntityById.orElse(null);
        Objects.requireNonNull(request).setStatus(Status.CANCELLED);
        requestRepository.save(request);
    }


    // this method will change the request status to completed will add the volunteer's id
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
