package org.example.util;

import org.example.dao.*;

public class EntityDAOFactory {

    public EntityDAOFactory() {}

    public ActorDAO getActorDAO() {return new ActorDAO();}

    public AddressDAO getAddressDAO() {
        return new AddressDAO();
    }

    public CategoryDAO getCategoryDAO() {
        return new CategoryDAO();
    }

    public CityDAO getCityDAO() {
        return new CityDAO();
    }

    public CountryDAO getCountryDAO() {
        return new CountryDAO();
    }

    public CustomerDAO getCustomerDAO() {
        return new CustomerDAO();
    }

    public FilmDAO getFilmDAO() {
        return new FilmDAO();
    }

    public FilmTextDAO getFilmTextDAO() {
        return new FilmTextDAO();
    }

    public InventoryDAO getInventoryDAO() {
        return new InventoryDAO();
    }

    public LanguageDAO getLanguageDAO() {
        return new LanguageDAO();
    }

    public PaymentDAO getPaymentDAO() {
        return new PaymentDAO();
    }

    public RentalDAO getRentalDAO() {
        return new RentalDAO();
    }

    public StaffDAO getStaffDAO() {
        return new StaffDAO();
    }

    public StoreDAO getStoreDAO() {
        return new StoreDAO();
    }
}
