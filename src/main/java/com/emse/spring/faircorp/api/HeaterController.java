package com.emse.spring.faircorp.api;

import com.emse.spring.faircorp.dao.RoomDao;
import com.emse.spring.faircorp.dao.HeaterDao;
import com.emse.spring.faircorp.model.Room;
import com.emse.spring.faircorp.model.Heater;
import com.emse.spring.faircorp.model.HeaterStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/heaters")
@Transactional
public class HeaterController {

    private final HeaterDao heaterDao;
    private final RoomDao roomDao;

    public HeaterController(HeaterDao heaterDao, RoomDao roomDao) { // (4)
        this.heaterDao = heaterDao;
        this.roomDao = roomDao;
    }

    @GetMapping
    public List<HeaterDto> findAll() {
        return heaterDao.findAll().stream().map(HeaterDto::new).collect(Collectors.toList());
    }

    @GetMapping(path = "/{id}")
    public HeaterDto findById(@PathVariable Long id) {
        return heaterDao.findById(id).map(HeaterDto::new).orElse(null);
    }

    @PutMapping(path = "/{id}/switch")
    public HeaterDto switchStatus(@PathVariable Long id) {
        Heater Heater = heaterDao.findById(id).orElseThrow(IllegalArgumentException::new);
        Heater.setHeaterStatus(Heater.getHeaterStatus() == HeaterStatus.OPEN ? HeaterStatus.CLOSED: HeaterStatus.OPEN);
        return new HeaterDto(Heater);
    }

    @PostMapping
    public HeaterDto create(@RequestBody HeaterDto dto) {


        Room room = roomDao.getOne(dto.getRoomId());

        Heater heater = null;
        if (dto.getId() == null) {
            heater = heaterDao.save(new Heater(room, dto.getName(), dto.getHeaterStatus()));
        }
        else {
            try {
                heater = heaterDao.getOne(dto.getId());
                heater.setHeaterStatus(dto.getHeaterStatus());
            }catch(Exception e) {
                System.out.print("Identifiant inexistant!\n");
            }
        }
        return new HeaterDto(heater);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Long id) {
        heaterDao.deleteById(id);
    }
}