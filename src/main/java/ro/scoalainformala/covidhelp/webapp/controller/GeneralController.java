package ro.scoalainformala.covidhelp.webapp.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@Log4j2
public class GeneralController {

    @RequestMapping(value = "/login", method = GET)
    public ModelAndView login() {
        ModelAndView login = new ModelAndView();
        login.setViewName("login");
        return login;
    }

    @RequestMapping(value = "/loginError", method = GET)
    public ModelAndView loginError() {
        ModelAndView loginError = new ModelAndView();
        loginError.setViewName("loginError");
        return loginError;
    }

    //you can remove this 2 method to create these controllers

    @RequestMapping(value = "/admin", method = GET)
    public ModelAndView admin() {
        ModelAndView admin = new ModelAndView();
        admin.setViewName("admin");
        return admin;
    }

}
