package ro.scoalainformala.covidhelp.webapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ro.scoalainformala.covidhelp.webapp.service.RequestService;

import java.util.Map;

@Controller
public class BrowseController {

    private final RequestService requestService;

    public BrowseController(RequestService requestService) {
        this.requestService = requestService;
    }


    @GetMapping(value = "/browse")
    public String browse(Model model) {
        model.addAttribute("requestList", requestService.getAvailableRequests());
        return "browse";
    }

    @GetMapping(value = "/browse", params = "county")
    public String browseByCounty(Model model,
                                 @RequestParam("county") String county) {
        model.addAttribute("requestList", requestService.getAvailableRequestsByCounty(county));
        return "browse";
    }

    //TODO: Implement filtering for request type
    //TODO: Implement more efficient filtering through only one mapping (Filter class)
    //TODO: Implement sorting for requests
    @GetMapping(value = "/browse", params = {"county", "city"})
    public String browseByCountyAndCity(Model model,
                                        @RequestParam("county") String county,
                                        @RequestParam("city") String city) {
        model.addAttribute("requestList", requestService.getAvailableRequestsByCountyAndCity(county, city));
        return "browse";
    }

    @PostMapping(path = "/browse", consumes = "application/json")
    public ResponseEntity<String> browseApply(@RequestBody Map<String, String> requestBody) {
        System.out.println(requestBody.get("id"));
        long requestId = Long.parseLong(requestBody.get("id"));
        long volunteerId = 9; //TODO: Get volunteer id through Spring Security
        requestService.addVolunteerToRequest(requestId, volunteerId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
