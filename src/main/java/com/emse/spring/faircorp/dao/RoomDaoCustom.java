package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.Room;
import com.emse.spring.faircorp.model.Window;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomDaoCustom  {

    Integer deleteWindowByRoom(Long id);

    Integer deleteHeaterByRoom(Long id);
}