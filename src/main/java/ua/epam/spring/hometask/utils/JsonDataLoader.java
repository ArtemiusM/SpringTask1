package ua.epam.spring.hometask.utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import ua.epam.spring.hometask.domain.Auditorium;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.EventRating;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;


public class JsonDataLoader {

    private static Set<Auditorium> auditoriumsCached;
    private static Set<Event> eventsCached;

    public JsonDataLoader(){
        loadAuditoriums();
        loadEvents();
    }

    public static Set<Auditorium> getAuditoriums() {
        Set<Auditorium> auditoriumsCopy = new HashSet<>();
        for(Auditorium a : auditoriumsCached){
            auditoriumsCopy.add(a.clone());
        }
        return auditoriumsCopy;
    }

    public static Set<Event> getEvents() {
        Set<Event> eventsCopy = new HashSet<>();
        for(Event e : eventsCached){
            eventsCopy.add(e.clone());
        }
        return eventsCopy;
    }


    public Set<Auditorium> loadAuditoriums(){

        Set<Auditorium> auditoriums = new HashSet<>();
        JSONParser parser = new JSONParser();

        // parsing file "Auditoriums.json"
        try {
            String path = JsonDataLoader.class.getClassLoader().getResource("resources/Auditoriums.json").getPath();
            JSONObject eventsJsonObj = (JSONObject) new JSONParser().parse(new FileReader(path));

            // loop array
            JSONArray eventsArray = (JSONArray) eventsJsonObj.get("auditoriums");
            Iterator<JSONObject> iterator = eventsArray.iterator();
            while (iterator.hasNext()) {
                JSONObject currEventObj = iterator.next();

                String name = (String) currEventObj.get("name");
                String numberOfSeats = (String) currEventObj.get("numberOfSeats");

                JSONArray vipSeatsArray = (JSONArray) currEventObj.get("vipSeats");
                Set<Long> vipSeats = (Set<Long>) vipSeatsArray.stream().map((s) -> Long.parseLong(s.toString())).collect(Collectors.toSet());

                Auditorium audObj = new Auditorium();
                audObj.setName(name);
                audObj.setNumberOfSeats(Long.parseLong(numberOfSeats));
                audObj.setVipSeats(vipSeats);
                auditoriums.add(audObj);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        JsonDataLoader.auditoriumsCached = auditoriums;
        return auditoriums;

    }

    public static Set<Event> loadEvents(){

        Set<Event> events = new HashSet<>();
        JSONParser parser = new JSONParser();

        // parsing file "Events.json"
        try {
            String path = JsonDataLoader.class.getClassLoader().getResource("resources/Events.json").getPath();
            JSONObject eventsJsonObj = (JSONObject) new JSONParser().parse(new FileReader(path));

            // loop array
            JSONArray eventsArray = (JSONArray) eventsJsonObj.get("events");
            Iterator<JSONObject> iterator = eventsArray.iterator();
            while (iterator.hasNext()) {
                JSONObject currEventObj = iterator.next();

                String name = (String) currEventObj.get("name");
                Double basePrice = Double.parseDouble((String) currEventObj.get("basePrice"));
                EventRating rating =  EventRating.valueOf((String) currEventObj.get("rating"));


                NavigableSet<LocalDateTime> airDates = new TreeSet<>();
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d.MM.yyyy");
                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("H:mm");

                JSONArray airDatesArray = (JSONArray) currEventObj.get("airDates");
                Iterator<JSONObject> adItr = airDatesArray.iterator();
                while (adItr.hasNext()){
                    JSONObject currAirDateObj = adItr.next();
                    String date = (String) currAirDateObj.get("date");
                    LocalDate localDate = LocalDate.parse(date, dateFormatter);
                    JSONArray timeArray = (JSONArray) currAirDateObj.get("time");
                    Iterator<String> timeItr = timeArray.iterator();
                    while (timeItr.hasNext()){
                        String currAirTimeObj = timeItr.next();
                        LocalTime localTime = LocalTime.parse(currAirTimeObj, timeFormatter);
                        LocalDateTime airDate = LocalDateTime.of(localDate, localTime);
                        airDates.add(airDate);
                    }
                }

                NavigableMap<LocalDateTime, Auditorium> auditoriums = new TreeMap<>();
                JSONArray auditoriumsArray = (JSONArray) currEventObj.get("auditoriums");
                Iterator<JSONObject> audItr = auditoriumsArray.iterator();
                while (audItr.hasNext()){
                    JSONObject currAuditoriumObj = audItr.next();
                    LocalDate localDate = LocalDate.parse(currAuditoriumObj.get("date").toString(), dateFormatter);
                    LocalTime localTime = LocalTime.parse(currAuditoriumObj.get("time").toString(), timeFormatter);
                    LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
                    String auditoriumName = currAuditoriumObj.get("auditorium").toString();
                    Auditorium auditorium = getByName(auditoriumName);
                    auditoriums.put(localDateTime, auditorium);
                }
                
                Event eventObj = new Event();
                eventObj.setName(name);
                eventObj.setBasePrice(basePrice);
                eventObj.setRating(rating);
                eventObj.setAuditoriums(auditoriums);
                eventObj.setAirDates(airDates);
                events.add(eventObj);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        JsonDataLoader.eventsCached = events;
        return events;

    }

    private static Auditorium getByName(String name){
        for(Auditorium a : auditoriumsCached){
            if(a.getName().equals(name)){
                return a.clone();
            }
        }
        return null;
    }
}
