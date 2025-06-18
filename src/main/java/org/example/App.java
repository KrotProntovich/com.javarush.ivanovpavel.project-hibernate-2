package org.example;

import com.mysql.cj.util.StringUtils;
import org.example.dao.*;
import org.example.entity.*;
import org.example.enums.*;
import org.example.service.WorkerDB;
import org.hibernate.Session;

import java.math.BigDecimal;
import java.time.Year;
import java.util.*;


public class App{


    public static void main( String[] args ){
        WorkerDB workerDB = new WorkerDB();

        // Проверка на поиск/добавление покупателя

//        long startMySQL1 = System.currentTimeMillis();
//        Customer customer1 = workerDB.creteCustomerInDB(1, "Maria", "Miller", "MARIA.MILLER@sakilacustomer.org",
//                "900 Santiago de Compostela Parkway", "Central Serbia", "Kragujevac", "Yugoslavia",
//                "93896", "716571220373");
//        long stopMySQL1 = System.currentTimeMillis();
//        System.out.println((stopMySQL1 - startMySQL1) + " " + customer1.getId() + "/" + customer1.getAddress().getId() + "/"
//                + customer1.getAddress().getCity().getId() + "/" + customer1.getAddress().getCity().getCountry().getId());


//        long startMySQL2 = System.currentTimeMillis();
//        Customer customer2 = workerDB.creteCustomerInDB(1, "Jon", "Jonson", "JONJONSON@josh.cam",
//                "666 AngelStreet", "DeadInside", "SinCity", "LostCountry",
//                "666", "6660000000");
//        long stopMySQL2 = System.currentTimeMillis();
//        System.out.println((stopMySQL2 - startMySQL2) + " " + customer2.getId() + "/" + customer2.getAddress().getId() + "/"
//                + customer2.getAddress().getCity().getId() + "/" + customer2.getAddress().getCity().getCountry().getId());

//        long startMySQL3 = System.currentTimeMillis();
//        Customer customer3 = workerDB.creteCustomerInDB(1, "Tod", "Jonson", "TODJONSON@josh.cam",
//                "999 LostStreet", "Lost", "LostCity", "LostCountry",
//                "999", "9990000000");
//        long stopMySQL3 = System.currentTimeMillis();
//        System.out.println((stopMySQL3 - startMySQL3) + " " + customer3.getId() + "/" + customer3.getAddress().getId() + "/"
//                + customer3.getAddress().getCity().getId() + "/" + customer3.getAddress().getCity().getCountry().getId());


        // Завершение аренды
//        String endOfRental = workerDB.endOfRental("DWAYNE", "OLVERA", "DWAYNE.OLVERA@sakilacustomer.org",
//                "ACADEMY DINOSAUR", 1);
//        System.out.println(endOfRental);

        // Начало аренды
//        String startOfRental = workerDB.startOfRental("DWAYNE", "OLVERA", "DWAYNE.OLVERA@sakilacustomer.org", "ACADEMY DINOSAUR", 1);
//        System.out.println(startOfRental);

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
//        String s = workerDB.addFilmInDB(name, description, year, languageName,
//                length, rating, features, actors, categories, countNewFilm);
//        System.out.println(s);

        workerDB.shutdown();

    }
}
