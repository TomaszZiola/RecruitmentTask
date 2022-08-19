package com.ziola.recruitmenttask.reservations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

interface ReservationService {

    void checkIfDatesAreCorrect(LocalDate fromDateRent, LocalDate toDateRent, Long objectToRentId);

    void convertToEntityAndSave(ReservationRequestDTO reservationDTO, BigDecimal howMuchToPay);

    Reservation findReservationById(Long reservationId);

    void updateEntityAndSave(Reservation reservation, UpdateReservationDTO updateReservationDTO, BigDecimal howMuchToPay);

    List<ReservationDTO> findAllReservationsByTenantName(String tenantName);

    List<ReservationDTO> findAllReservationsByObjectId(Long objectId);

    ReservationResponseDTO takeAndProceedDTO(ReservationRequestDTO reservationRequestDTO);

    ReservationResponseDTO takeDTOUpdateReservation(UpdateReservationDTO updateReservationDTO);
}
