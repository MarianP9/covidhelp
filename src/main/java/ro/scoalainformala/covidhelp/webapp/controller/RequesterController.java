package ro.scoalainformala.covidhelp.webapp.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ro.scoalainformala.covidhelp.webapp.domain.Request;
import ro.scoalainformala.covidhelp.webapp.service.RequesterService;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Controller
@RequestMapping(value = "/requester")
@Log4j2
public class RequesterController {

    @Autowired
    RequesterService requesterService;

    @RequestMapping(method = GET)
    public ModelAndView requesterName() {
        ModelAndView request = new ModelAndView();

        String email = requesterService.getEmail();
        String firstName = requesterService.firstName(email);
        String lastName = requesterService.lastName(email);
        List<Request> requestActiveList = requesterService.getRequestActive(email);
        List<Request> requestInactiveList = requesterService.getRequestInactive(email);
        List<Request> getActiveRequests = requesterService.getRequestWithVolunteer(email);
        List<Integer> size = requesterService.getRequestWithVolunteerSize(email);
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

    @RequestMapping(value = "/request", method = POST)
    public ModelAndView request() {
        ModelAndView request = new ModelAndView();
        request.setViewName("request");
        return request;
    }

}
