package ua.epam.spring.hometask;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.epam.spring.hometask.DAO.AuditoriumDAOImpl;
import ua.epam.spring.hometask.DAO.EventDAOImpl;
import ua.epam.spring.hometask.domain.Auditorium;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.service.EventService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

public class App {

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext a = new ClassPathXmlApplicationContext("resources/Spring.xml");
        App app = (App) a.getBean("app");
        AuditoriumDAOImpl audImpl = (AuditoriumDAOImpl) a.getBean("auditoriumDAOImpl");
        EventDAOImpl evtImpl = (EventDAOImpl) a.getBean("eventDAOImpl");
        EventService evtService = (EventService) a.getBean("eventServiceImpl");

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d.MM.yyyy");
        LocalDate from = LocalDate.parse( "12.06.2018",dateFormatter);
        LocalDate to = LocalDate.parse( "18.06.2018",dateFormatter);
        Set<Event> eventsInRange = evtService.getForDateRange(from, to);

        Set<Auditorium> a2 = audImpl.getAll();
        Set<Event> e2 = evtImpl.getAll();
        int b =1 ;
    }

}
