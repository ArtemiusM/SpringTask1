package ua.epam.spring.hometask;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.epam.spring.hometask.DAO.AuditoriumDAOImpl;
import ua.epam.spring.hometask.domain.Auditorium;

import java.util.Set;

public class App {

    public static void main(String[] args) {
        ApplicationContext a = new ClassPathXmlApplicationContext("resources/Spring.xml");
        App app = (App) a.getBean("app");
        AuditoriumDAOImpl aimpl = (AuditoriumDAOImpl) a.getBean("auditoriumDAOImpl");
        Set<Auditorium> a2 = aimpl.getAll();
        int b =1 ;
    }

}
