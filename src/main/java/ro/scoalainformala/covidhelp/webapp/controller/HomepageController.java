package ro.scoalainformala.covidhelp.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ro.scoalainformala.covidhelp.webapp.service.AccountService;
import ro.scoalainformala.covidhelp.webapp.service.RequestService;

@Controller
public class HomepageController {

    private final RequestService requestService;
    private final AccountService accountService;

    public HomepageController(RequestService requestService, AccountService accountService) {
        this.requestService = requestService;
        this.accountService = accountService;
    }

    @GetMapping(value = "/")
    public String homepage(Model model){
        model.addAttribute("approvedRequests", requestService.getApprovedRequestsCount());
        model.addAttribute("completedRequests", requestService.getCompletedRequestsCount());
        model.addAttribute("volunteersCount", accountService.getVolunteersCount());
        return "homepage";
    }
}
