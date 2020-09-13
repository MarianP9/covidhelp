package ro.scoalainformala.covidhelp.webapp.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ro.scoalainformala.covidhelp.webapp.domain.Account;
import ro.scoalainformala.covidhelp.webapp.service.GeneralService;

import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@Log4j2
public class GeneralController {

    @Autowired
    GeneralService generalService;

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

    @RequestMapping(value = "/loginError", method = GET)
    public ModelAndView loginError() {
        ModelAndView loginError = new ModelAndView();
        loginError.addObject("invalid", "Email or password is incorrect! Please try again!");
        loginError.setViewName("login");
        return loginError;
    }

    @RequestMapping(value = "/register", method = GET)
    public ModelAndView register() {
        ModelAndView register = new ModelAndView();
        register.addObject("account", new Account());
        register.setViewName("register");
        return register;
    }

    @RequestMapping(value = "/saveUser", method = POST)
    public ModelAndView save(@Valid @ModelAttribute(value = "account") Account account, BindingResult result) {
        ModelAndView save = new ModelAndView();
        if (result.hasErrors()) {
            save.setViewName("register");
            return save;
        }
        generalService.add(account);
        save.setViewName("successful");
        return save;
    }

    //you can remove this 2 method to create these controllers

    @RequestMapping(value = "/admin", method = GET)
    public ModelAndView admin() {
        ModelAndView admin = new ModelAndView();
        admin.setViewName("admin");
        return admin;
    }

}
