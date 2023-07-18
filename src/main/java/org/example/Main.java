package org.example;

import org.hibernate.SessionFactory;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Конфігурація Hibernate
        HibernateUtil hibernateUtil = HibernateUtil.getInstance();
        SessionFactory sessionFactory = hibernateUtil.getSessionFactory();

        // CRUD сервіс для клієнтів
        ClientCrudService clientCrudService = new ClientCrudService(sessionFactory);

        // CRUD сервіс для планет
        PlanetCrudService planetCrudService = new PlanetCrudService(sessionFactory);

        // Додавання клієнтів
        Client client1 = new Client();
        client1.setName("John Doe");
        clientCrudService.saveClient(client1);

        Client client2 = new Client();
        client2.setName("Alice Johnson");
        clientCrudService.saveClient(client2);

        // Виведення всіх клієнтів
        System.out.println("Всі клієнти:");
        List<Client> clients = clientCrudService.getAllClients();
        for (Client client : clients) {
            System.out.println(client);
        }

        // Додавання планет
        Planet planet1 = new Planet();
        planet1.setId("MARS");
        planet1.setName("Mars");
        planetCrudService.savePlanet(planet1);

        Planet planet2 = new Planet();
        planet2.setId("VEN");
        planet2.setName("Venus");
        planetCrudService.savePlanet(planet2);

        // Виведення всіх планет
        System.out.println("\nВсі планети:");
        List<Planet> planets = planetCrudService.getAllPlanets();
        for (Planet planet : planets) {
            System.out.println(planet);
        }

        // Видалення клієнта та планети
        clientCrudService.deleteClient(client1);
        planetCrudService.deletePlanet(planet2);

        // Виведення оновлених списків
        System.out.println("\nОновлені списки:");
        clients = clientCrudService.getAllClients();
        for (Client client : clients) {
            System.out.println(client);
        }

        planets = planetCrudService.getAllPlanets();
        for (Planet planet : planets) {
            System.out.println(planet);
        }

        // Закриття ресурсів Hibernate
        hibernateUtil.close();
    }
}
