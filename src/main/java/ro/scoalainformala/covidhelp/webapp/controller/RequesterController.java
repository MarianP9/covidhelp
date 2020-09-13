package ro.scoalainformala.covidhelp.webapp.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ro.scoalainformala.covidhelp.webapp.dto.RequestViewDto;
import ro.scoalainformala.covidhelp.webapp.domain.Request;
import ro.scoalainformala.covidhelp.webapp.domain.RequestType;
import ro.scoalainformala.covidhelp.webapp.domain.Status;
import ro.scoalainformala.covidhelp.webapp.service.AccountService;
import ro.scoalainformala.covidhelp.webapp.service.RequestService;
import ro.scoalainformala.covidhelp.webapp.service.RequestTypeService;
import ro.scoalainformala.covidhelp.webapp.service.RequesterService;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Controller
@RequestMapping(value = "/requester")
@Log4j2
public class RequesterController {

    @Autowired
    AccountService accountService;
    @Autowired
    RequestService requestService;
    @Autowired
    RequestTypeService requestTypeService;
    @Autowired
    RequesterService requesterService;

    @RequestMapping(method = GET)
    public ModelAndView requesterName() {
        ModelAndView request = new ModelAndView();

        String email = accountService.getEmail();
        String firstName = requesterService.firstName(email);
        String lastName = requesterService.lastName(email);
        List<RequestViewDto> requestActiveList = requesterService.getRequestActive(email);
        List<RequestViewDto> requestInactiveList = requesterService.getRequestInactive(email);
        List<RequestViewDto> getActiveRequests = requesterService.getRequestWithVolunteer(email);
        List<Integer> size = requesterService.getRequestWithVolunteerSize(email);
        requesterService.getAllRequestViewDtoList();
        request.addObject("fn", firstName);
        request.addObject("ln", lastName);
        request.addObject("activeList", requestActiveList);
        request.addObject("inactiveList", requestInactiveList);
        if (size.size() != 0) {
            request.addObject("active", getActiveRequests);
            request.addObject("size", size);
            request.setViewName("requester");
        } else {
            request.setViewName("requesterNull");
        }
        return request;
    }

    @RequestMapping(value = "{id}", method = GET)
    public String cancelRequest(@PathVariable Long id) {
        requesterService.cancelRequest(id);
        return "redirect:/requester";
    }

    @RequestMapping(value = "/completed/{idr}/{idv}", method = GET)
    public String completedRequest(@PathVariable Long idr, @PathVariable Long idv) {
        requesterService.completeRequest(idr, idv);
        return "redirect:/requester";
    }

    @RequestMapping(value = "/profile", method = POST)
    public ModelAndView profile() {
        ModelAndView profile = new ModelAndView();
        profile.setViewName("profile");
        return profile;
    }

    @GetMapping("/placeRequest")
    public ModelAndView placeRequestForm() {
        ModelAndView requestForm = new ModelAndView();
        requestForm.setViewName("placeRequest");
        requestForm.addObject("request", new Request());
        requestForm.addObject("requestTypes", requestTypeService.getAll());
        return requestForm;
    }

    @PostMapping("/placeRequest")
    public ModelAndView placeRequestSubmit(@ModelAttribute("request") Request request) {
        request.setRequester(accountService.getAccountByEmail(accountService.getEmail()));
        request.setStatus(Status.PENDING);
        requestService.addRequest(request);
        return new ModelAndView("redirect:/requester");
    }

}
