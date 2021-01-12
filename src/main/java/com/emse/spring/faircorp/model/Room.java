package com.emse.spring.faircorp.model;

import org.hibernate.annotations.Target;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity//(1)
public  class Room {
    @Id//(2)
    @GeneratedValue
    private Long id;
    @Column(name ="floor",nullable=false)
    private Integer floor;
    @Column(nullable=false)
    private String name;
    @OneToMany(mappedBy = "room")
    private List<Heater> heaters;
    @OneToMany(mappedBy="room")
    private Set<Window> window;
    @Column(name ="current_temperature")
    private Double currentTemp;
    @Column(name ="Target_temperature" )
    private Double temp;

    public Room() {}

    public Room(String name, Integer floor) {
        this.name = name;
        this.floor = floor;
    }

    public List<Heater> getHeaters() {
        return heaters;
    }

    public void setHeaters(List<Heater> heaters) {
        this.heaters = heaters;
    }

    public  Room(String name){
        this.name=name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getCurrentTemp() {
        return currentTemp;
    }

    public void setCurrentTemp(Double currentTemp) {
        this.currentTemp = currentTemp;
    }

    public Double getTemp() {
        return temp;
    }

    public void setTmp(Double temp) {
        this.temp = temp;
    }

    public Set<Window> getWindow() {
        return window;
    }

    public void setWindow(Set<Window> window) {
        this.window = window;
    }

}