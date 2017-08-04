package com.grancircus.spring.controller;


import com.test.models.UserDAO;
import com.test.models.RegistrationEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.ArrayList;

@Controller
public class HomeController {

    private final UserDAO userDAO = new UserDAO();

    @RequestMapping("/")
    public ModelAndView newUser()
    {
        return new ModelAndView("welcome", "addingUser", "");
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.GET)
    public ModelAndView addUser(@RequestParam("firstname") String firstname,
                                @RequestParam("lastname") String lastname,
                                @RequestParam("address1") String address1,
                                @RequestParam("address2") String address2,
                                @RequestParam("city") String city,
                                @RequestParam("state") String state,
                                @RequestParam("zip") int zip,
                                @RequestParam("country") String country)
    {
        RegistrationEntity newUser = userDAO.addUser(firstname, lastname, address1, address2, city, state, zip, country);


        return new ModelAndView("confirmation", "addingUser", newUser);
    }

    @RequestMapping("/adminpage")
    public ModelAndView listUsers()
    {
        ArrayList<RegistrationEntity> userList = userDAO.getRegistrationEntitiesByDate();

        return new ModelAndView("adminpage","uList", userList);
    }

}