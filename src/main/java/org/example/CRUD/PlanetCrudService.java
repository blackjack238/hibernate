package org.example.CRUD;

import org.example.CRUD.CrudOperationException;
import org.example.entitiy.Planet;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.List;

public class PlanetCrudService {
    private final SessionFactory sessionFactory;

    public PlanetCrudService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void savePlanet(Planet planet) throws CrudOperationException {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            session.saveOrUpdate(planet);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            throw new CrudOperationException("Помилка при збереженні клієнта.", e);
        }
    }


    public void updatePlanet(Planet planet) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(planet);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deletePlanet(Planet planet) throws CrudOperationException {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            session.delete(planet);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            throw new CrudOperationException("Помилка при видаленні клієнта.", e);
        }
    }

    public Planet getPlanetById(String id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Planet.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Planet> getAllPlanets() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Planet", Planet.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}