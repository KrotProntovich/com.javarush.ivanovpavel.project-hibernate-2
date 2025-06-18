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
import static java.util.Objects.nonNull;

public class WorkerDB {
    private final SessionFactory sessionFactory;
    private final EntityDAOFactory entityDAOFactory;
    private final EntityFactory entityFactory;


    public WorkerDB() {
        sessionFactory = MySessionFactory.getSessionFactory();
        entityDAOFactory = new EntityDAOFactory(sessionFactory);
        entityFactory = new EntityFactory();
    }

    // Получение страны из БД
    private Country getCountryFromDB(String countryName) {
            return entityDAOFactory.getCountryDAO().findByName(countryName);
    }

    // Получение города из DB
    private City getCityFromDB(String cityName, Short countryId) {
        return entityDAOFactory.getCityDAO().findByNameAndCountryID(cityName, countryId);
    }

    // Получение покупателя из DB
    private Customer getCustomerFromDB(String firstName, String lastName, String email, Integer storeId) {
        return entityDAOFactory.getCustomerDAO().findByFirstLastNameAndEmailAndStore(firstName.toUpperCase(), lastName.toUpperCase(), email, storeId);
    }

    // Создать покупателя в БД
    public Customer creteCustomerInDB(Integer storeId, String firstName, String lastName, String email,
                                      String addressName, String district, String cityName, String countryName, String postalCode, String phone) {
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            Customer customer = getCustomerFromDB(firstName.toUpperCase(), lastName.toUpperCase(), email, storeId);
            if (isNull(customer)) {
                Country country = getCountryFromDB(countryName);
                City city;
                if (isNull(country)) {
                    country = entityFactory.createCountry(countryName);
                    city = entityFactory.createCity(cityName, country);
                }
                else  {
                    city = getCityFromDB(cityName, country.getId());
                    if (isNull(city)) {
                        city = entityFactory.createCity(cityName, country);
                    }
                }
                Store store = entityDAOFactory.getStoreDAO().getById(Long.valueOf(storeId));
                Address address = entityFactory.createAddress(addressName, district, city, phone);
                address.setPostalCode(postalCode);
                customer = entityFactory.createCustomer(store, firstName.toUpperCase(), lastName.toUpperCase(), email, address);
                entityDAOFactory.getCountryDAO().persist(country);
                entityDAOFactory.getCityDAO().persist(city);
                entityDAOFactory.getAddressDAO().persist(address);
                entityDAOFactory.getCustomerDAO().persist(customer);
            }
            transaction.commit();
            return customer;
        }
    }

    // Получить фильм из БД
    private Film getFilmFromDB(String filmName) {
            return entityDAOFactory.getFilmDAO().findByName(filmName);
    }

    // Завершение аренды
    public String endOfRental(String firstName, String lastName, String email, String filmName, Integer storeId) {
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            Customer customer = getCustomerFromDB(firstName, lastName, email, storeId);
            if (isNull(customer)) {
                return "Customer not found";
            }
            Film film = getFilmFromDB(filmName);
            if (isNull(film)) {
                return "Film not found";
            }
            List<Integer> inventoryId = entityDAOFactory.getInventoryDAO().findInventoryIdByFilmID(film.getId());
            RentalDAO rentalDAO = entityDAOFactory.getRentalDAO();
            Rental rental = rentalDAO.findByInventoryIDAndCustomerID(inventoryId, customer.getId());
            if (isNull(rental)) {
                return "Rental not found";
            }
            rental.setReturnDate(LocalDateTime.now());
            rentalDAO.merge(rental);
            transaction.commit();
            return "Rental №" + rental.getId() + " has been successfully ended";
        }
    }

    // Поиск фильмов доступных в данном магазине для аренды
    private List<Integer> searchMoviesInStore(Short filmId, Integer storeId) {
        List<Integer> result = entityDAOFactory.getInventoryDAO().findInventoryIDByFilmIDAndStoreID(filmId, storeId);
        String hql = "select r.inventory.id from Rental r where r.inventory.id in(:inventoryId) and r.returnDate IS NULL";
        List<Integer> rentalInventoryID = sessionFactory.getCurrentSession().createQuery(hql, Integer.class)
                .setParameter("inventoryId", result)
                .list();
        result.removeAll(rentalInventoryID);
        return result;
    }


    // Начало аренды
    public String startOfRental(String firstName, String lastName, String email, String filmName, Integer storeId) {
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            Film film = getFilmFromDB(filmName);
            if (isNull(film)) {
                return "Film not found";
            }
            List<Integer> inventoryId = searchMoviesInStore(film.getId(), storeId);
            if (inventoryId.isEmpty()) {
                return "No movies to rent";
            }
            Customer customer = getCustomerFromDB(firstName, lastName, email, storeId);
            if (isNull(customer)) {
                return "Customer not found";
            }
            Inventory inventory = entityDAOFactory.getInventoryDAO().getById(Long.valueOf(inventoryId.get(0)));
            Staff staff = entityDAOFactory.getStoreDAO().getById(Long.valueOf(storeId)).getStaff();
            Rental rental = entityFactory.createRental(inventory, customer, staff);
            Payment payment = entityFactory.createPayment(customer, staff, BigDecimal.valueOf(6.66));
            payment.setRental(rental);
            entityDAOFactory.getRentalDAO().persist(rental);
            entityDAOFactory.getPaymentDAO().persist(payment);
            transaction.commit();
            return "Rental №" + rental.getId() + " has been successfully started.\n" + "Payment №" + payment.getId();
        }
    }

    // Получение языка из БД или создание нового
    private Language getLanguageFromDB(String languageName) {
        return entityDAOFactory.getLanguageDAO().findLanguageByName(languageName);
    }

    // Создание списка актеров
    private Set<Actor> getActorsList(List<String> actorsFirstLastName) {
        Set<Actor> actors = new HashSet<>();
        for (String actorFullName : actorsFirstLastName) {
            List<String> actorFirstLastName = Stream.of(actorFullName.split(" ")).collect(Collectors.toList());
            String firstName = actorFirstLastName.get(0).toUpperCase();
            String lastName = actorFirstLastName.get(1).toUpperCase();
            Actor actor = entityDAOFactory.getActorDAO().findByName(firstName, lastName);
            if (actor == null) {
                actor = entityFactory.createActor(firstName, lastName);
            }
            actors.add(actor);
        }
        return actors;
    }

    // Создание списка категорий
    private Set<Category> getCategoriesList(List<String> categoriesName) {
        Set<Category> categories = new HashSet<>();
        for (String categoryName : categoriesName) {
            Category category = entityDAOFactory.getCategoryDAO().findByName(categoryName);
            if (category == null) {
                category = entityFactory.createCategory(categoryName);
            }
            categories.add(category);
        }
        return categories;
    }

    // Добавить фильм в инвентарь магазина
    private void addFilmToStoreInventory(Store store, Film film, Integer countFilm) {
        for (int i = 0; i < countFilm; i++)
            entityDAOFactory.getInventoryDAO().persist(entityFactory.createInventory(film, store));
    }

    // Добавление нового фильма
    public String addFilmInDB(String title, String description, Year release, String languageName,
                              Short length, Rating rating, Set<Feature> features, List<String> actorsFirstLastName,
                              List<String> categoriesName, Integer countNewFilm) {
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            if (!isNull(getFilmFromDB(title))) {
                return "Film already exists";
            }
            Language language = getLanguageFromDB(languageName);
            if (isNull(language)) {
                language = entityFactory.createLanguage(languageName);
            }
            Film film = entityFactory.createFilm(title, description, release, language,
                    length, rating, features);

            entityDAOFactory.getLanguageDAO().persist(language);
            entityDAOFactory.getFilmDAO().persist(film);
            FilmText filmText = entityFactory.createFilmText(film);
            entityDAOFactory.getFilmTextDAO().persist(filmText);
            Set<Actor> actors = getActorsList(actorsFirstLastName);
            Set<Category> categories = getCategoriesList(categoriesName);
            actors.forEach(actor -> {
                actor.getFilms().add(film);
                entityDAOFactory.getActorDAO().persist(actor);
            });
            categories.forEach(category -> {
                category.getFilms().add(film);
                entityDAOFactory.getCategoryDAO().persist(category);
            });
            entityDAOFactory.getLanguageDAO().persist(language);
            List<Store> stores = entityDAOFactory.getStoreDAO().getAll();
            stores.forEach((store) -> addFilmToStoreInventory(store, film, countNewFilm));
            transaction.commit();
            return "Film " + film.getId() + " has been successfully added";
        }
    }

    public void shutdown() {
        if (nonNull(sessionFactory)) {
            sessionFactory.close();
        }
    }
}
