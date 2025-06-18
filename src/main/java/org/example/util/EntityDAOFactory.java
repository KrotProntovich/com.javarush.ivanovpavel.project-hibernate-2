package org.example.util;

import org.example.dao.*;
import org.hibernate.SessionFactory;

public class EntityDAOFactory {

    private final SessionFactory sessionFactory;

    public EntityDAOFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public ActorDAO getActorDAO() {return new ActorDAO(sessionFactory);}

    public AddressDAO getAddressDAO() {
        return new AddressDAO(sessionFactory);
    }

    public CategoryDAO getCategoryDAO() {
        return new CategoryDAO(sessionFactory);
    }

    public CityDAO getCityDAO() {
        return new CityDAO(sessionFactory);
    }

    public CountryDAO getCountryDAO() {
        return new CountryDAO(sessionFactory);
    }

    public CustomerDAO getCustomerDAO() {
        return new CustomerDAO(sessionFactory);
    }

    public FilmDAO getFilmDAO() {
        return new FilmDAO(sessionFactory);
    }

    public FilmTextDAO getFilmTextDAO() {
        return new FilmTextDAO(sessionFactory);
    }

    public InventoryDAO getInventoryDAO() {
        return new InventoryDAO(sessionFactory);
    }

    public LanguageDAO getLanguageDAO() {
        return new LanguageDAO(sessionFactory);
    }

    public PaymentDAO getPaymentDAO() {
        return new PaymentDAO(sessionFactory);
    }

    public RentalDAO getRentalDAO() {
        return new RentalDAO(sessionFactory);
    }

    public StaffDAO getStaffDAO() {
        return new StaffDAO(sessionFactory);
    }

    public StoreDAO getStoreDAO() {
        return new StoreDAO(sessionFactory);
    }
}
