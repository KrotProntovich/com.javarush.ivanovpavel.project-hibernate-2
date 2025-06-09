package org.example.service;

import org.example.config.MySessionFactory;
import org.example.entity.*;
import org.example.dao.*;
import org.example.enums.*;
import org.example.util.*;
import org.hibernate.*;

import java.math.BigDecimal;
import java.time.*;
import java.util.*;
import java.util.stream.*;

import static java.util.Objects.isNull;

public class WorkerDB {
    private final SessionFactory sessionFactory;
    private final EntityDAOFactory entityDAOFactory;
    private final EntityFactory entityFactory;


    public WorkerDB() {
        sessionFactory = MySessionFactory.getSessionFactory();
        entityDAOFactory = new EntityDAOFactory();
        entityFactory = new EntityFactory();
    }

    // Получение страны из БД
    public Country getCountryFromDB(String countryName) {
        try (Session session = sessionFactory.openSession()) {
            return entityDAOFactory.getCountryDAO().findByName(session, countryName);
        }
    }

    // Создание страны в БД
    public Country createCountryFromDB(String name) {
        try (Session session = sessionFactory.openSession()) {
            Country country = entityFactory.createCountry(name);
            session.beginTransaction();
            entityDAOFactory.getCountryDAO().persist(session, country);
            session.getTransaction().commit();
            return country;
        }
    }

    // Получение города из DB
    public City getCityFromDB(String cityName, String countryName) {
        try (Session session = sessionFactory.openSession()) {
            return entityDAOFactory.getCityDAO()
                    .findByNameAndCountryID(session, cityName,
                            entityDAOFactory.getCountryDAO().findByName(session, countryName).getId());
        }
    }

    // Создание города в БД
    public City createCityInDB(String cityName, String countryName) {
        try (Session session = sessionFactory.openSession()) {
            Country country = getCountryFromDB(countryName);
            if (isNull(country)) {
                country = createCountryFromDB(countryName);
            }
            City city = getCityFromDB(cityName, countryName);
            if (isNull(city)) {
                city = entityFactory.createCity(cityName, country);
                session.beginTransaction();
                entityDAOFactory.getCityDAO().persist(session, city);
                session.getTransaction().commit();
            }
            return city;
        }
    }

    // Получение адреса из БД
    public Address getAddressFromDB(String addressName, String phone) {
        try (Session session = sessionFactory.openSession()) {
            return entityDAOFactory.getAddressDAO().findByNameAndPhone(session, addressName, phone);
        }
    }

    // Создание адреса в БД
    public Address createAddressInDB(String addressName, String addressName2, String district,
                                     String cityName, String countryName, String postalCode, String phone) {
        try (Session session = sessionFactory.openSession()) {
            City city = null;
            if (isNull(getCountryFromDB(countryName))) {
                createCountryFromDB(countryName);
                city = createCityInDB(addressName, countryName);
            } else {
                city = getCityFromDB(cityName, countryName);
                if (isNull(city)) {
                    city = createCityInDB(addressName, countryName);
                }
            }
            Address address = getAddressFromDB(addressName, phone);
            if (isNull(address)) {
                address = entityFactory.createAddress(addressName, addressName2, district, city, postalCode, phone);
                session.beginTransaction();
                entityDAOFactory.getAddressDAO().persist(session, address);
                session.getTransaction().commit();
            }
            return address;
        }
    }

    // Получение покупателя из DB
    public Customer getCustomerFromDB(String firstName, String lastName, String email, Integer storeId) {
        try (Session session = sessionFactory.openSession()) {
            return entityDAOFactory.getCustomerDAO().findByFirstLastNameAndEmailAndStore(session,
                    firstName.toUpperCase(), lastName.toUpperCase(), email, storeId);
        }
    }

    // Создать покупателя в БД
    public Customer creteCustomerInDB(Integer storeId, String firstName, String lastName, String email,
                                      String addressName, String addressName2, String district,
                                      String cityName, String countryName, String postalCode, String phone) {
        try (Session session = sessionFactory.openSession()) {
            Store store = entityDAOFactory.getStoreDAO().getById(session, Long.valueOf(storeId));
            Address address = null;
            if (isNull(getCountryFromDB(countryName))) {
                createCountryFromDB(countryName);
                createCityInDB(addressName, countryName);
                address = createAddressInDB(addressName, addressName2, district, cityName, countryName, postalCode, phone);
            } else {
                if (isNull(getCityFromDB(cityName, countryName))) {
                    createCityInDB(addressName, countryName);
                    address = createAddressInDB(addressName, addressName2, district, cityName, countryName, postalCode, phone);
                } else {
                    address = getAddressFromDB(addressName, phone);
                    if (isNull(address)) {
                        address = createAddressInDB(addressName, addressName2, district, cityName, countryName, postalCode, phone);
                    }
                }
            }
            Customer customer = getCustomerFromDB(firstName.toUpperCase(), lastName.toUpperCase(), email, storeId);
            if (isNull(customer)) {
                customer = entityFactory.createCustomer(store, firstName.toUpperCase(), lastName.toUpperCase(), email, address);
                session.beginTransaction();
                entityDAOFactory.getCustomerDAO().persist(session, customer);
                session.getTransaction().commit();
            }
            return customer;
        }
    }

    // Получить фильм из БД
    public Film getFilmFromDB(String filmName) {
        try (Session session = sessionFactory.openSession()) {
            return entityDAOFactory.getFilmDAO().findByName(session, filmName);
        }
    }

    // Завершение аренды
    public String endOfRental(String firstName, String lastName, String email, String filmName, Integer storeId) {
        try (Session session = sessionFactory.openSession()) {
            Customer customer = getCustomerFromDB(firstName, lastName, email, storeId);
            if (isNull(customer)) {
                return "Customer not found";
            }
            Film film = getFilmFromDB(filmName);
            if (isNull(film)) {
                return "Film not found";
            }
            List<Integer> inventoryId = entityDAOFactory.getInventoryDAO().findInventoryIdByFilmID(session, film.getId());
            RentalDAO rentalDAO = entityDAOFactory.getRentalDAO();
            Rental rental = rentalDAO.findByInventoryIDAndCustomerID(session, inventoryId, customer.getId());
            if (isNull(rental)) {
                return "Rental not found";
            }
            rental.setReturnDate(LocalDateTime.now());
            session.beginTransaction();
            rentalDAO.merge(session, rental);
            session.getTransaction().commit();
            return "Rental №" + rental.getId() + " has been successfully ended";
        }
    }

    // Начало аренды
    public String startOfRental(String firstName, String lastName, String email, String filmName, Integer storeId) {
        try (Session session = sessionFactory.openSession()) {
            Film film = getFilmFromDB(filmName);
            if (isNull(film)) {
                return "Film not found";
            }
            List<Integer> inventoryId = searchMoviesInStore(session, film.getId(), storeId);
            if (inventoryId.isEmpty()) {
                return "No movies to rent";
            }
            Customer customer = getCustomerFromDB(firstName, lastName, email, storeId);
            if (isNull(customer)) {
                return "Customer not found";
            }
            Inventory inventory = entityDAOFactory.getInventoryDAO().getById(session, Long.valueOf(inventoryId.get(0)));
            Staff staff = entityDAOFactory.getStoreDAO().getById(session, Long.valueOf(storeId)).getStaff();
            Rental rental = entityFactory.createRental(inventory, customer, staff);
            Payment payment = entityFactory.createPayment(customer, staff, rental, BigDecimal.valueOf(6.66));
            session.beginTransaction();
            entityDAOFactory.getRentalDAO().persist(session, rental);
            entityDAOFactory.getPaymentDAO().persist(session, payment);
            session.getTransaction().commit();
            return "Rental №" + rental.getId() + " has been successfully started.\n" + "Payment №" + payment.getId();
        }
    }

    // Добавление нового фильма
    public String addFilmInDB(String title, String description, Year release, String languageName,
                              String originalLanguageName, Byte rentalDuration, BigDecimal rentalRate,
                              Short length, BigDecimal replacementCost, Rating rating,
                              Set<Feature> specialFeatures, List<String> actorsFirstLastName,
                              List<String> categoriesName, Integer countFilm) {
        try (Session session = sessionFactory.openSession()) {
            if (!isNull(getFilmFromDB(title))) {
                return "Film already exists";
            }
            Language language = getLanguageFromDB(session, languageName);
            Language originalLanguage = null;
            if (!isNull(originalLanguageName)) {
                originalLanguage = getLanguageFromDB(session, originalLanguageName);
            }
            Film film = entityFactory.createFilm(title, description, release, language, originalLanguage, rentalDuration, rentalRate,
                    length, replacementCost, rating, specialFeatures);
            Set<Actor> actors = getActorsList(session, actorsFirstLastName);
            Set<Category> categories = getCategoriesList(session, categoriesName);
            session.beginTransaction();
            actors.forEach(actor -> {
                actor.getFilms().add(film);
                entityDAOFactory.getActorDAO().persist(session, actor);
            });
            categories.forEach(category -> {
                category.getFilms().add(film);
                entityDAOFactory.getCategoryDAO().persist(session, category);
            });
            LanguageDAO languageDAO = entityDAOFactory.getLanguageDAO();
            languageDAO.persist(session, language);
            if (!isNull(originalLanguage)) {
                languageDAO.persist(session, originalLanguage);
            }
            entityDAOFactory.getFilmDAO().persist(session, film);
            FilmText filmText = entityFactory.createFilmText(film);
            entityDAOFactory.getFilmTextDAO().persist(session, filmText);
            List<Store> stores = entityDAOFactory.getStoreDAO().getAll(session);
            stores.forEach((store) -> addFilmToStoreInventory(session, store, film, countFilm));
            session.getTransaction().commit();
            return "Film " + film.getId() + " has been successfully added";
        }
    }

    // Добавить фильм в инвентарь магазина
    public void addFilmToStoreInventory(Session session, Store store, Film film, Integer countFilm) {
        for (int i = 0; i < countFilm; i++)
            entityDAOFactory.getInventoryDAO().persist(session, entityFactory.createInventory(film, store));
    }

    // Поиск фильмов доступных в данном магазине для аренды
    private List<Integer> searchMoviesInStore(Session session, Short filmId, Integer storeId) {
        List<Integer> result = entityDAOFactory.getInventoryDAO().findInventoryIDByFilmIDAndStoreID(session, filmId, storeId);
        String hql = "select r.inventory.id from Rental r where r.inventory.id in(:inventoryId) and r.returnDate IS NULL";
        List<Integer> rentalInventoryID = session.createQuery(hql, Integer.class)
                .setParameter("inventoryId", result)
                .list();
        result.removeAll(rentalInventoryID);
        return result;
    }

    // Создание списка актеров
    private Set<Actor> getActorsList(Session session, List<String> actorsFirstLastName) {
        Set<Actor> actors = new HashSet<>();
        for (String actorFullName : actorsFirstLastName) {
            List<String> actorFirstLastName = Stream.of(actorFullName.split(" ")).collect(Collectors.toList());
            String firstName = actorFirstLastName.get(0).toUpperCase();
            String lastName = actorFirstLastName.get(1).toUpperCase();
            Actor actor = entityDAOFactory.getActorDAO().findByName(session, firstName, lastName);
            if (actor == null) {
                actor = entityFactory.createActor(firstName, lastName);
            }
            actors.add(actor);
        }
        return actors;
    }

    // Создание списка категорий
    private Set<Category> getCategoriesList(Session session, List<String> categoriesName) {
        Set<Category> categories = new HashSet<>();
        for (String categoryName : categoriesName) {
            Category category = entityDAOFactory.getCategoryDAO().findByName(session, categoryName);
            if (category == null) {
                category = entityFactory.createCategory(categoryName);
            }
            categories.add(category);
        }
        return categories;
    }

    // Получение языка из БД или создание нового
    public Language getLanguageFromDB(Session session, String languageName) {
        Language language = entityDAOFactory.getLanguageDAO().findLanguageByName(session, languageName);
        if (isNull(language)) {
            language = entityFactory.createLanguage(languageName);
        }
        return language;
    }
}
