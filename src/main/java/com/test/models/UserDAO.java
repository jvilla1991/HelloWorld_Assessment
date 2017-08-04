package com.test.models;

/**
 * Created by Admin on 8/3/2017.
 */
import com.test.models.RegistrationEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

public class UserDAO {
    public UserDAO() {
    }

    public ArrayList<RegistrationEntity> getRegistrationEntitiesByDate() {
        Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
        SessionFactory sessionFact = cfg.buildSessionFactory();
        Session selectUsers = sessionFact.openSession();
        selectUsers.beginTransaction();
        Criteria u = selectUsers.createCriteria(RegistrationEntity.class).addOrder(Order.desc("date"));
        ArrayList<RegistrationEntity> userList = (ArrayList<RegistrationEntity>) u.list();

        return userList;
    }

    public RegistrationEntity addUser(@RequestParam("firstname") String firstname,
                                             @RequestParam("lastname") String lastname,
                                             @RequestParam("address1") String address1,
                                             @RequestParam("address2") String address2,
                                             @RequestParam("city") String city,
                                             @RequestParam("state") String state,
                                             @RequestParam("zip") int zip,
                                             @RequestParam("country") String country)

    {
        Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
        SessionFactory sessionFact = cfg.buildSessionFactory();
        Session session = sessionFact.openSession();

        Transaction tx = session.beginTransaction();

        RegistrationEntity newUser = new RegistrationEntity();

        newUser.setFirstname(firstname);
        newUser.setLastname(lastname);
        newUser.setAddress1(address1);
        newUser.setAddress2(address2);
        newUser.setCity(city);
        newUser.setState(state);
        newUser.setZip(zip);
        newUser.setCountry(country);

        session.save(newUser);
        tx.commit();
        session.close();
        return newUser;
    }

}
