package ro.scoalainformala.covidhelp.webapp.service;

import ro.scoalainformala.covidhelp.webapp.dto.RequestViewDto;

import java.util.List;

public interface RequesterService {

    String firstName(String email);
    String lastName(String email);
    List<RequestViewDto> getRequestActive(String email);
    List<RequestViewDto> getRequestInactive(String email);
    List<RequestViewDto> getRequestWithVolunteer(String email);
    List<Integer> getRequestWithVolunteerSize(String email);
    void cancelRequest(Long id);
    void completeRequest(Long idr, Long idv);
    List<RequestViewDto> getAllRequestViewDtoList();

}
