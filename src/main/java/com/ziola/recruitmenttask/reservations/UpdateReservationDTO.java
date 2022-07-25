package com.ziola.recruitmenttask.reservations;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class UpdateReservationDTO {

    private Long reservationId;

    private LocalDate fromDateRent;

    private LocalDate toDateRent;
}
