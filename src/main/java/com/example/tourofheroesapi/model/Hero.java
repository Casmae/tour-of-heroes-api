package com.example.tourofheroesapi.model;

import javax.persistence.*;

@Entity
@Table(name= "Hero")
public class Hero {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "alterEgo")
    private String alterEgo;
    @Column(name = "power")
    private String power;

    @Column(name = "picture")
    private String picture;

    public Hero(String name, String alterEgo, String power, String picture) {
        this.name = name;
        this.alterEgo = alterEgo;
        this.power = power;
        this.picture = picture;
    }

    public Hero() {

    }

    public long getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlterEgo() {
        return alterEgo;
    }

    public void setAlterEgo(String alterEgo) {
        this.alterEgo = alterEgo;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return "Hero{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", alterEgo='" + alterEgo + '\'' +
                ", power='" + power + '\'' +
                ", picture='" + picture + '\'' +
                '}';
    }
}
