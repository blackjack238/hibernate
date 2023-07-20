package org.example;

import org.example.CRUD.ClientCrudService;
import org.example.CRUD.CrudOperationException;
import org.example.CRUD.PlanetCrudService;
import org.example.CRUD.TicketCrudService;
import org.example.Hibernate.HibernateUtil;
import org.example.entitiy.Client;
import org.example.entitiy.Planet;
import org.example.entitiy.Ticket;

public class Main {
    public static void main(String[] args) throws CrudOperationException {
        // Ініціалізуємо HibernateUtil
        HibernateUtil hibernateUtil = HibernateUtil.getInstance();

        // Створюємо клієнтів і планети
        Client client1 = new Client("John Doe");
        Client client2 = new Client("Jane Smith");
        Planet planet1 = new Planet("MARS", "Mars");
        Planet planet2 = new Planet("VEN", "Venus");

        // Зберігаємо клієнтів і планети
        ClientCrudService clientCrudService = new ClientCrudService(hibernateUtil.getSessionFactory());
        PlanetCrudService planetCrudService = new PlanetCrudService(hibernateUtil.getSessionFactory());
        clientCrudService.saveClient(client1);
        clientCrudService.saveClient(client2);
        planetCrudService.savePlanet(planet1);
        planetCrudService.savePlanet(planet2);

        // Створюємо квитки з різними ситуаціями
        Ticket ticket1 = new Ticket(client1, planet1, planet2);
        Ticket ticket2 = new Ticket(null, planet1, planet2); // квиток з null клієнтом
        Ticket ticket3 = new Ticket(client1, null, planet2); // квиток з null початковою планетою
        Ticket ticket4 = new Ticket(client1, planet1, null); // квиток з null кінцевою планетою
        Ticket ticket5 = new Ticket(null, null, null); // квиток зі всіма null полями

        // Зберігаємо квитки і очікуємо помилку при кожному некоректному квитку
        TicketCrudService ticketCrudService = new TicketCrudService(hibernateUtil.getSessionFactory());
        try {
            ticketCrudService.saveTicket(ticket1);
            System.out.println("Квиток 1 успішно збережено.");
        } catch (CrudOperationException e) {
            System.err.println("Помилка при збереженні квитка 1: " + e.getMessage());
        }

        try {
            ticketCrudService.saveTicket(ticket2);
            System.out.println("Квиток 2 успішно збережено.");
        } catch (CrudOperationException e) {
            System.err.println("Помилка при збереженні квитка 2: " + e.getMessage());
        }

        try {
            ticketCrudService.saveTicket(ticket3);
            System.out.println("Квиток 3 успішно збережено.");
        } catch (CrudOperationException e) {
            System.err.println("Помилка при збереженні квитка 3: " + e.getMessage());
        }

        try {
            ticketCrudService.saveTicket(ticket4);
            System.out.println("Квиток 4 успішно збережено.");
        } catch (CrudOperationException e) {
            System.err.println("Помилка при збереженні квитка 4: " + e.getMessage());
        }

        try {
            ticketCrudService.saveTicket(ticket5);
            System.out.println("Квиток 5 успішно збережено.");
        } catch (CrudOperationException e) {
            System.err.println("Помилка при збереженні квитка 5: " + e.getMessage());
        }

        // Закриваємо ресурси
        hibernateUtil.close();
    }}


