package com.ziola.recruitmenttask.reservations;

import org.springframework.stereotype.Service;

@Service
public class ConverterReservationEntityIntoDTO {

    public ReservationDTO convertEntity(Reservation reservation) {
        return new ReservationDTO.Builder()
                .landlordName(reservation.getLandlord().getName())
                .objectToRentName(reservation.getObjectToRent().getName())
                .tenantName(reservation.getTenant().getName())
                .rentCost(reservation.getRentCost())
                .fromDateRent(reservation.getFromDateRent())
                .toDateRent(reservation.getToDateRent())
                .build();
    }
}
