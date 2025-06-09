package org.example;

import com.mysql.cj.util.StringUtils;
import org.example.config.MySessionFactory;
import org.example.dao.*;
import org.example.entity.*;
import org.example.enums.Feature;
import org.example.enums.Rating;
import org.example.service.WorkerDB;
import org.hibernate.Session;

import java.math.BigDecimal;
import java.time.Year;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class App{


    public static void main( String[] args ){
        WorkerDB workerDB = new WorkerDB();

        // Проверка на поиск/добавление страны
//        Country country1 = workerDB.getCountryFromDB("Slovakia");
//        System.out.println(country1.getId()); //
//        Country country2 = workerDB.createCountryFromDB("LostCountry");
//        System.out.println(country2.getId());

        // Проверка на поиск/добавление города
//        City city1 = workerDB.getCityFromDB("Zaria", "Nigeria");
//        System.out.println(city1.getId() + "/" + city1.getCountry().getId());
//        City city2 = workerDB.createCityInDB("SinCity", "LostCountry");
//        System.out.println(city2.getId() + "/" + city2.getCountry().getId());

        // Проверка на поиск/добавление адреса
//        Address address1 = workerDB.getAddressFromDB("1531 Sal Drive", "648856936185");
//        System.out.println(address1.getId() + "/" + address1.getCity().getId() + "/" + address1.getCity().getCountry().getId());
//        Address address2 = workerDB.createAddressInDB("666 AngelStreet", "", "DeadInside",
//                "SinCity","LostCountry", "69696", "9996669966");
//        System.out.println(address2.getId() + "/" + address2.getCity().getId() + "/" + address2.getCity().getCountry().getId());


        // Проверка на поиск/добавление покупателя
//        Customer customer1 = workerDB.getCustomerFromDB("Joann", "Gardner",
//                "JOANN.GARDNER@sakilacustomer.org", 2);
//        System.out.println(customer1.getId() + "/" + customer1.getAddress().getId() + "/"
//                + customer1.getAddress().getCity().getId() + "/" + customer1.getAddress().getCity().getCountry().getId());

//        Customer customer2 = workerDB.creteCustomerInDB(1, "Tod", "Jon", "TODNOJHON@josh.cam",
//                "666 AngelStreet", "", "DeadInside", "SinCity", "LostCountry",
//                "69696", "9996669966");
//        System.out.println(customer2.getId() + "/" + customer2.getAddress().getId() + "/"
//                + customer2.getAddress().getCity().getId() + "/" + customer2.getAddress().getCity().getCountry().getId());


        // Завершение аренды
//        String s = workerDB.endOfRental("DWAYNE", "OLVERA", "DWAYNE.OLVERA@sakilacustomer.org",
//                "ACADEMY DINOSAUR", 1);
//        System.out.println(s);

        // Начало аренды
//        String s = workerDB.startOfRental("Tod", "Jon", "TODNOJHON@josh.cam", "ACADEMY DINOSAUR", 1);
//        System.out.println(s);

        // Новый фильм в прокате
//        String name = "Shooter";
//        String description = "Опытный снайпер Боб Ли оказывается втянутым в заговор с целью убийства президента. Похоже, что его хотят подставить и «сдать» властям, поэтому ему необходимо как можно быстрее найти и обезвредить настоящего убийцу…";
//        Year year = Year.of(2007);
//        String languageName = "English";
//        String originalLanguageName = null;
//        Byte rentalDuration = 0;
//        BigDecimal rentalRate = BigDecimal.ZERO;
//        Short length = 124;
//        BigDecimal replacementCost = BigDecimal.ZERO;
//        Rating rating = Rating.R;
//        Set<Feature> features = new HashSet<>();
//        features.add(Feature.TRAILERS);
//        features.add(Feature.COMMENTARIES);
//        List<String> actors = new ArrayList<>();
//        actors.add("Mark Wahlberg");
//        actors.add("Kate Mara");
//        actors.add("Danny Glover");
//        actors.add("Penelope Guiness");
//        List<String> categories = new ArrayList<>();
//        categories.add("Action");
//        categories.add("Thriller");
//        categories.add("Mystery");
//        Integer countNewFilm = 3;
//
//        String s = workerDB.addFilmInDB(name, description, year, languageName, originalLanguageName, rentalDuration, rentalRate,
//                length, replacementCost, rating, features, actors, categories, countNewFilm);
//        System.out.println(s);

    }
}
