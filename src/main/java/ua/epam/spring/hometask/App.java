package ua.epam.spring.hometask;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.epam.spring.hometask.DAO.AuditoriumDAOImpl;
import ua.epam.spring.hometask.DAO.EventDAOImpl;
import ua.epam.spring.hometask.domain.Auditorium;
import ua.epam.spring.hometask.domain.Event;

import java.util.Set;

public class App {

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext a = new ClassPathXmlApplicationContext("resources/Spring.xml");
        App app = (App) a.getBean("app");
        AuditoriumDAOImpl audImpl = (AuditoriumDAOImpl) a.getBean("auditoriumDAOImpl");
        EventDAOImpl evtImpl = (EventDAOImpl) a.getBean("eventDAOImpl");
//        Thread.sleep(5000);
        Set<Auditorium> a2 = audImpl.getAll();
        Set<Event> e2 = evtImpl.getAll();
        int b =1 ;
    }

}
