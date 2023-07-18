package org.example;

import lombok.Getter;


import org.flywaydb.core.Flyway;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
    private static final HibernateUtil INSTANCE;

    private final SessionFactory sessionFactory;

    static {
        INSTANCE = new HibernateUtil();
    }

    private HibernateUtil() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // Завантажуємо конфігурацію з файлу hibernate.cfg.xml
                .build();

        Metadata metadata = new MetadataSources(registry)
                .addAnnotatedClass(Client.class)
                .addAnnotatedClass(Planet.class)
                .getMetadataBuilder()
                .build();

        sessionFactory = metadata.getSessionFactoryBuilder().build();
    }

    public static HibernateUtil getInstance() {
        return INSTANCE;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void close() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
