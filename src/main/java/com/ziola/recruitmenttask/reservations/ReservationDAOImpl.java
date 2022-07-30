package com.ziola.recruitmenttask.reservations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class ReservationDAOImpl implements ReservationDAO {

    private final EntityManager entityManager;

    @Override
    public Reservation findReservationById(Long reservationId) {
        return entityManager.find(Reservation.class, reservationId);
    }

    @Transactional
    @Override
    public void save(Reservation reservation) {
        entityManager.persist(reservation);
    }

    @Override
    public List<Reservation> findAllReservationsByObjectId(Long id) {
        Query query = entityManager.createQuery("FROM Reservation where object_to_rent_id =:objectId");
        query.setParameter("objectId", id);
        return query.getResultList();
    }
}

