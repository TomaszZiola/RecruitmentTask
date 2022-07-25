package com.ziola.recruitmenttask.reservations;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class ReservationRequestDTO {

    private LocalDate fromDateRent;

    private LocalDate toDateRent;

    private Long objectToRentId;

    private String nameOfTenant;

//    private String nameOfLandlord;
}
