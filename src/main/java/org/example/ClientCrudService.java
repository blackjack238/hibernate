package org.example;

import org.example.Client;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.List;

public class ClientCrudService {
    private final SessionFactory sessionFactory;

    public ClientCrudService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void saveClient(Client client) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(client);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void updateClient(Client client) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(client);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteClient(Client client) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(client);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Client getClientById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Client.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Client> getAllClients() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Client", Client.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
