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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.ArrayList;

@Controller
public class HomeController {

    private final UserDAO userDAO = new UserDAO();

    // This request mapping is responsible for rendering the view entitled "welcome"
    @RequestMapping("/")
    public ModelAndView newUser()
    {
        return new ModelAndView("welcome", "", "");
    }

    // Add User parses the form on the Welcome page after the submit button is pressed
    // Notice that date is not included, The server is intended to return a NULL value to the date column
    // and mysql has a trigger that replaces null with a current timestamp
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
        // Server side validation using regex ensures that string that have no letters (i.e. names)
        // are properly handled with custom responses
        if (firstname.matches(".*\\d.*"))
        {
            return new ModelAndView("welcome", "strerr", "First Name must not contain numbers");
        }
        else if (lastname.matches(".*\\d.*"))
        {
            return new ModelAndView("welcome", "strerr", "Last Name must not contain numbers");
        }
        else if (city.matches(".*\\d.*"))
        {
            return new ModelAndView("welcome", "strerr", "City must not contain numbers");
        }
        else if (state.matches(".*\\d.*"))
        {
            return new ModelAndView("welcome", "strerr", "State must not contain numbers");
        }

        // We send all the requirements for a new user to our Data Access Object, which will then take
        // all the user input and send it to MySQL
        RegistrationEntity newUser = userDAO.addUser(firstname, lastname, address1, address2, city, state, zip, country);

        // Assuming all validation is correct, we return a confirmation page which will display the Strings
        // firstname and lastname
        return new ModelAndView("confirmation", "addingUser", newUser);
    }

    @RequestMapping("/adminpage")
    public ModelAndView listUsers()
    {
        // Once again, we use our Data Access Object to reach into the database. The Criteria for
        // ordering the data by descending date order is in the DAO
        ArrayList<RegistrationEntity> userList = userDAO.getRegistrationEntitiesByDate();

        return new ModelAndView("adminpage","uList", userList);
    }

}