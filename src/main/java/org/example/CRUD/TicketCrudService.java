package org.example.CRUD;
import org.example.CRUD.CrudOperationException;
import org.example.entitiy.Ticket;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Objects;

public class TicketCrudService {
    private final SessionFactory sessionFactory;

    public TicketCrudService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void saveTicket(Ticket ticket) throws CrudOperationException {
        if (ticket.getClient() == null || Objects.isNull(ticket.getClient().getId())) {
            throw new CrudOperationException("Неможливо зберегти квиток без клієнта.");
        }

        if (ticket.getFromPlanet() == null || ticket.getFromPlanet().getId() == null ||
                ticket.getToPlanet() == null || ticket.getToPlanet().getId() == null) {
            throw new CrudOperationException("Неможливо зберегти квиток без початкової або кінцевої планети.");
        }

        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(ticket);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new CrudOperationException("Помилка при збереженні квитка: " + e.getMessage(), e);
        }
    }

    public Ticket getTicketById(int id) throws CrudOperationException {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Ticket.class, id);
        } catch (Exception e) {
            throw new CrudOperationException("Помилка при отриманні квитка за ID: " + e.getMessage(), e);
        }
    }

    public List<Ticket> getAllTickets() throws CrudOperationException {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Ticket", Ticket.class).list();
        } catch (Exception e) {
            throw new CrudOperationException("Помилка при отриманні всіх квитків: " + e.getMessage(), e);
        }
    }

    public void updateTicket(Ticket ticket) throws CrudOperationException {
        if (ticket.getClient() == null || Objects.isNull(ticket.getClient().getId())) {
            throw new CrudOperationException("Неможливо оновити квиток без клієнта.");
        }

        if (ticket.getFromPlanet() == null || ticket.getFromPlanet().getId() == null ||
                ticket.getToPlanet() == null || ticket.getToPlanet().getId() == null) {
            throw new CrudOperationException("Неможливо оновити квиток без початкової або кінцевої планети.");
        }

        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(ticket);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new CrudOperationException("Помилка при оновленні квитка: " + e.getMessage(), e);
        }
    }

    public void deleteTicket(Ticket ticket) throws CrudOperationException {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(ticket);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new CrudOperationException("Помилка при видаленні квитка: " + e.getMessage(), e);
        }
    }
}
