package ro.scoalainformala.covidhelp.webapp.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Controller
@RequestMapping(value = "/requester")
@Log4j2
public class RequesterController {

    @RequestMapping(method = GET)
    public ModelAndView requesterName() {
        ModelAndView req = new ModelAndView();
        req.setViewName("requester");
        return req;
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
