package org.example;

import org.example.entity.Actor;
import org.example.entity.Film;
import org.example.enums.Feature;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test {
    public static void main(String[] args) {
        List<String> actorsFullName = new ArrayList<>();
        actorsFullName.add("Mark Wahlberg".toUpperCase());
        actorsFullName.add("Kate Mara".toUpperCase());
        actorsFullName.add("Daniel Glover".toUpperCase());

        Set<Actor> actors = new HashSet<>();
        for(String actorFullName : actorsFullName){
            List<String> actorFirstLastName = Stream.of(actorFullName.split(" ")).collect(Collectors.toList());
            Actor actor = new Actor();
            actor.setFirstName(actorFirstLastName.get(0));
            actor.setLastName(actorFirstLastName.get(1));
            actors.add(actor);
        }
        actors.forEach(actor ->  System.out.println(actor.getFirstName() + " " + actor.getLastName()));
    }
}
