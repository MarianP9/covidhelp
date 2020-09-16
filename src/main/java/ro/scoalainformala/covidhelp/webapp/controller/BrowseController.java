package ro.scoalainformala.covidhelp.webapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ro.scoalainformala.covidhelp.webapp.service.AccountService;
import ro.scoalainformala.covidhelp.webapp.service.RequestService;
import ro.scoalainformala.covidhelp.webapp.service.RequestTypeService;

import java.util.Map;

@Controller
public class BrowseController {

    private final RequestService requestService;
    private final AccountService accountService;
    private final RequestTypeService requestTypeService;

    public BrowseController(RequestService requestService, AccountService accountService, RequestTypeService requestTypeService) {
        this.requestService = requestService;
        this.accountService = accountService;
        this.requestTypeService = requestTypeService;
    }


    @GetMapping(value = "/browse")
    public ModelAndView browse() {
        ModelAndView browseView = new ModelAndView();
        browseView.setViewName("browse");
        browseView.addObject("requestTypes", requestTypeService.getAll());
        browseView.addObject("requestList", requestService.getAvailableRequests());
        return browseView;
    }


    @GetMapping(value = "/browse", params = {"county", "city", "type"})
    public ModelAndView browseByCountyAndCity(@RequestParam("county") String county,
                                              @RequestParam("city") String city,
                                              @RequestParam("type") String type) {
        ModelAndView browseView = new ModelAndView();
        browseView.setViewName("browse");
        browseView.addObject("requestTypes", requestTypeService.getAll());
        browseView.addObject("requestList", requestService.getFilteredAvailableRequests(county, city, type));
        return browseView;
    }

    @PostMapping(path = "/browse", consumes = "application/json")
    public ResponseEntity<String> browseApply(@RequestBody Map<String, String> requestBody) {
        System.out.println(requestBody.get("id"));
        long requestId = Long.parseLong(requestBody.get("id"));
        long volunteerId = accountService.getAccountByEmail(accountService.getEmail()).getId();
        requestService.addVolunteerToRequest(requestId, volunteerId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
