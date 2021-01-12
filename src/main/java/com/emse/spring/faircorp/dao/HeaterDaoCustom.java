package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.Heater;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HeaterDaoCustom{
    List<Heater> findRoomOpenHeaters(Long id);

    Integer deleteHeaterByRoom(Long room_id);
}