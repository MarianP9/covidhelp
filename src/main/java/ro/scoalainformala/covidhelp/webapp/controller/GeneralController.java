package ro.scoalainformala.covidhelp.webapp.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@Log4j2
public class GeneralController {

    @RequestMapping(value = "/", method = GET)
    public ModelAndView homepage() {
        ModelAndView homepage = new ModelAndView();
        homepage.setViewName("index");
        return homepage;
    }

    @RequestMapping(value = "/login", method = GET)
    public ModelAndView login() {
        ModelAndView login = new ModelAndView();
        login.setViewName("login");
        return login;
    }

    //you can remove this 2 method to create these controllers

    @RequestMapping(value = "/volunteer", method = GET)
    public ModelAndView volunteer() {
        ModelAndView vol = new ModelAndView();
        vol.setViewName("volunteer");
        return vol;
    }

    @RequestMapping(value = "/admin", method = GET)
    public ModelAndView admin() {
        ModelAndView admin = new ModelAndView();
        admin.setViewName("admin");
        return admin;
    }

}
