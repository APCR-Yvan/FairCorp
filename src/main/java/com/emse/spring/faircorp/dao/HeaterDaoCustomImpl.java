package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.Heater;
import com.emse.spring.faircorp.model.HeaterStatus;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class HeaterDaoCustomImpl implements HeaterDaoCustom{

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Heater> findRoomOpenHeaters(Long id) {
        String jpql = "select h from Heater h where h.room.id = :id and h.heaterStatus= :status";
        return em.createQuery(jpql, Heater.class)
                .setParameter("id", id)
                .setParameter("status", HeaterStatus.OPEN)
                .getResultList();
    }


    @Override
    public Integer deleteHeaterByRoom(Long id) {
        String jpql = "DELETE FROM Heater h WHERE h.room.id = :id";
        Query query = em.createQuery(jpql);
        Integer del = query.setParameter("id", id).executeUpdate();
        return del;
    }

}