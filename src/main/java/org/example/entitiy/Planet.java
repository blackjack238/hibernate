package org.example.entitiy;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Planet {
    @Id
    private String id;
    @Column
    private String name;


    public Planet() {
    }

    // Конструктор з параметрами
    public Planet(String id, String name) {
        this.id = id;
        this.name = name;
    }

    // Геттери та сеттери
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


