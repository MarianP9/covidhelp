package ro.scoalainformala.covidhelp.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
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
    public ModelAndView homepage(){
        ModelAndView homepage = new ModelAndView();
        homepage.setViewName("homepage");
        homepage.addObject("volunteersCount", accountService.getVolunteersCount());
        homepage.addObject("completedRequests", requestService.getCompletedRequestsCount());
        homepage.addObject("shoppingCount", requestService.getShoppingRequestsCount());
        homepage.addObject("petwalkingCount", requestService.getPetwalkingRequestsCount());
        return homepage;
    }
}
