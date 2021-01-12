package com.emse.spring.faircorp.api;

import com.emse.spring.faircorp.model.Room;


public class RoomDto {
    private Long id;
    private String name;
    private Double currentTemp;
    private Integer floor;


    public RoomDto() {
    }

    public RoomDto(Room room) {

        this.name = room.getName();
        this.id = room.getId();
        this.currentTemp = room.getCurrentTemp();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Double getCurrentTemp() {
        return currentTemp;
    }
    public void setCurrentTemp(Double currentTemp) {
        this.currentTemp = currentTemp;
    }


    public void setName(String name) {
        this.name = name;
    }


    public Integer getFloor() {
        return floor;
    }
}