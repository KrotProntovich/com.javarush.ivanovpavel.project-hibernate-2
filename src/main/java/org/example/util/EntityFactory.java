package org.example.util;

import org.example.entity.*;
import org.example.enums.*;

import java.math.BigDecimal;
import java.time.Year;
import java.util.Set;

public class EntityFactory {
    public EntityFactory() {}

    // Создание объекта актер
    public Actor createActor(String firstName, String lastName) {
        Actor actor = new Actor();
        actor.setFirstName(firstName);
        actor.setLastName(lastName);
        return actor;
    }

    // Создание объекта адрес
    public Address createAddress(String addressName, String address2, String district, City city, String postalCode, String phone) {
        Address address = new Address();
        address.setAddress(addressName);
        address.setAddress2(address2);
        address.setDistrict(district);
        address.setCity(city);
        address.setPostalCode(postalCode);
        address.setPhone(phone);
        return address;
    }

    // Создание объекта категория
    public Category createCategory(String name) {
        Category category = new Category();
        category.setName(name);
        return category;
    }

    // Создание объекта город
    public City createCity(String cityName, Country country) {
        City city = new City();
        city.setCity(cityName);
        city.setCountry(country);
        return city;
    }

    // Создание объекта страна
    public Country createCountry(String name) {
        Country country = new Country();
        country.setName(name);
        return country;
    }

    // Создание объекта покупатель
    public Customer createCustomer(Store store, String firstName, String lastName, String email, Address address) {
        Customer customer = new Customer();
        customer.setStore(store);
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setEmail(email);
        customer.setAddress(address);
        return customer;
    }

    // Создание объекта фильм
    public Film createFilm(String title, String description, Year release,
                            Language language, Language originalLanguage, Byte rentalDuration,
                            BigDecimal rentalRate, Short length, BigDecimal replacementCost, Rating rating,
                            Set<Feature> specialFeatures) {
        Film film = new Film();
        film.setTitle(title);
        film.setDescription(description);
        film.setRelease(release);
        film.setLanguage(language);
        film.setOriginalLanguage(originalLanguage);
        film.setRentalDuration(rentalDuration);
        film.setRentalRate(rentalRate);
        film.setLength(length);
        film.setReplacementCost(replacementCost);
        film.setRating(rating);
        film.setSpecialFeatures(specialFeatures);
        return film;
    }

    // Создание объекта filmText
    public FilmText createFilmText(Film film) {
        FilmText filmText = new FilmText();
        filmText.setId(film.getId());
        filmText.setFilm(film);
        filmText.setTitle(film.getTitle());
        filmText.setDescription(film.getDescription());
        return filmText;
    }

    // Создание объекта язык
    public Language createLanguage(String languageName) {
        Language language = new Language();
        language.setName(languageName);
        return language;
    }

    // Создание объекта инвентарь
    public Inventory createInventory(Film film, Store store) {
        Inventory inventory = new Inventory();
        inventory.setFilm(film);
        inventory.setStore(store);
        return inventory;
    }

    // Создание объекта оплаты
    public Payment createPayment(Customer customer, Staff staff, Rental rental, BigDecimal amount) {
        Payment payment = new Payment();
        payment.setCustomer(customer);
        payment.setStaff(staff);
        payment.setRental(rental);
        payment.setAmount(amount);
        return payment;
    }

    // Создание объекта аренды
    public Rental createRental(Inventory inventory, Customer customer, Staff staff) {
        Rental rental = new Rental();
        rental.setInventory(inventory);
        rental.setCustomer(customer);
        rental.setStaff(staff);
        return rental;

    }
}
