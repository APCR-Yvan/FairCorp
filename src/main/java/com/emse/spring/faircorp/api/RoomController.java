package com.emse.spring.faircorp.api;

import com.emse.spring.faircorp.dao.HeaterDao;
import com.emse.spring.faircorp.dao.RoomDao;
import com.emse.spring.faircorp.dao.WindowDao;
import com.emse.spring.faircorp.model.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin
@RestController // (1)
@RequestMapping("/api/rooms") // (2)
@Transactional // (3)
public class RoomController {

    private final RoomDao roomDao;

    public RoomController(RoomDao roomDao) { // (4)
        this.roomDao = roomDao;
    }

    @GetMapping // (5)
    public List<RoomDto> findAll() {
        return roomDao.findAll().stream().map(RoomDto::new).collect(Collectors.toList());  // (6)
    }

    @GetMapping(path = "/{id}")
    public RoomDto findById(@PathVariable Long id) {
        return roomDao.findById(id).map(RoomDto::new).orElse(null); // (7)
    }

    @PutMapping(path = "/{id}/switchWindows")
    public RoomDto switchWindowsStatus(@PathVariable Long id) {
        Room room = roomDao.findById(id).orElseThrow(IllegalArgumentException::new);
        Set<Window> windows = room.getWindow();
        for (Window window : windows) {
            window.setWindowStatus(window.getWindowStatus() == WindowStatus.OPEN ? WindowStatus.CLOSED: WindowStatus.OPEN);
            //System.out.println(window.getWindowStatus());
        }
        return new RoomDto(room);
    }

    @PutMapping(path = "/{id}/switchSingleWindow/{idw}")
    public RoomDto switchSingleWindowStatus(@PathVariable Long id, @PathVariable Long idw) {
        Room room = roomDao.findById(id).orElseThrow(IllegalArgumentException::new);
        Set<Window> windows = room.getWindow();
        for (Window window : windows) {
            if(idw == window.getId()){
                window.setWindowStatus(window.getWindowStatus() == WindowStatus.OPEN ? WindowStatus.CLOSED: WindowStatus.OPEN);
            }
            //System.out.println(window.getWindowStatus());
        }
        return new RoomDto(room);
    }

    @PutMapping(path = "/{id}/switchHeaters")
    public RoomDto switchHeatersStatus(@PathVariable Long id) {
        Room room = roomDao.findById(id).orElseThrow(IllegalArgumentException::new);
        Set<Heater> heaters = (Set<Heater>) room.getHeaters();
        for (Heater heater : heaters) {
            heater.setHeaterStatus(heater.getHeaterStatus() == HeaterStatus.OPEN ? HeaterStatus.CLOSED: HeaterStatus.OPEN);
            //System.out.println(heater.getHeaterStatus());
        }
        return new RoomDto(room);
    }

    @PutMapping(path = "/{id}/switchSingleHeater/{idh}")
    public RoomDto switchSingleHeaterStatus(@PathVariable Long id, @PathVariable Long idh) {
        Room room = roomDao.findById(id).orElseThrow(IllegalArgumentException::new);
        Set<Heater> heaters = (Set<Heater>) room.getHeaters();
        for (Heater heater : heaters) {
            if(idh == heater.getId()){
                heater.setHeaterStatus(heater.getHeaterStatus() == HeaterStatus.OPEN ? HeaterStatus.CLOSED: HeaterStatus.OPEN);
            }
        }
        return new RoomDto(room);
    }

    @PostMapping // (8)
    public RoomDto create(@RequestBody RoomDto dto) {
        // RoomDto must always contain the Room Room


        Room Room = roomDao.getOne(dto.getId());

        Room room = null;
        // On creation id is not defined
        if (dto.getId() == null) {
            room = roomDao.save(new Room(dto.getName(), dto.getFloor()));
        }
        else {
            try {
                room = roomDao.getOne(dto.getId());  // (9)
            }catch(Exception e) {
                System.out.print("Identifiant inexistant!\n");
            }
        }
        return new RoomDto(Room);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Long id) {
        roomDao.deleteWindowByRoom(id);
        roomDao.deleteHeaterByRoom(id);
        roomDao.deleteById(id);
    }
}