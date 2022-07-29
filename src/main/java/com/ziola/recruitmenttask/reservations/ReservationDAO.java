package com.ziola.recruitmenttask.reservations;

import java.util.List;

public interface ReservationDAO {

    Reservation findReservationById(Long reservationId);

    void save(Reservation reservation);

    List<Reservation> findAllReservationsByObjectId(Long objectId);
}
