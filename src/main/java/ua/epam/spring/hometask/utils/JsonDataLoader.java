package ua.epam.spring.hometask.utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import ua.epam.spring.hometask.domain.Auditorium;
import ua.epam.spring.hometask.domain.Event;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;


public class JsonDataLoader {

    public static Set<Auditorium> loadAuditoriums(){

        Set<Auditorium> auditoriums = new HashSet<>();
        JSONParser parser = new JSONParser();

        // parsing file "Auditoriums.json"
        try {
            String path = JsonDataLoader.class.getClassLoader().getResource("resources/Auditoriums.json").getPath();
            JSONObject auditoriumsJsonObj = (JSONObject) new JSONParser().parse(new FileReader(path));

            // loop array
            JSONArray auditoriumsArray = (JSONArray) auditoriumsJsonObj.get("auditoriums");
            Iterator<JSONObject> iterator = auditoriumsArray.iterator();
            while (iterator.hasNext()) {
                JSONObject currObj = iterator.next();

                String name = (String) currObj.get("name");
                String numberOfSeats = (String) currObj.get("numberOfSeats");

                JSONArray vipSeatsArray = (JSONArray) currObj.get("vipSeats");
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
        return auditoriums;

    }

    public static Set<Event> loadEvents(){

        Set<Auditorium> auditoriums = new HashSet<>();
        JSONParser parser = new JSONParser();

        // parsing file "Auditoriums.json"
        try {
            String path = JsonDataLoader.class.getClassLoader().getResource("resources/Auditoriums.json").getPath();
            JSONObject auditoriumsJsonObj = (JSONObject) new JSONParser().parse(new FileReader(path));

            // loop array
            JSONArray auditoriumsArray = (JSONArray) auditoriumsJsonObj.get("auditoriums");
            Iterator<JSONObject> iterator = auditoriumsArray.iterator();
            while (iterator.hasNext()) {
                JSONObject currObj = iterator.next();

                String name = (String) currObj.get("name");
                String numberOfSeats = (String) currObj.get("numberOfSeats");

                JSONArray vipSeatsArray = (JSONArray) currObj.get("vipSeats");
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
        return auditoriums;

    }
}
