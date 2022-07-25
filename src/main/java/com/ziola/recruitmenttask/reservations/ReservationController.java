package com.ziola.recruitmenttask.reservations;

import com.ziola.recruitmenttask.objectstorent.ObjectToRentService;
import com.ziola.recruitmenttask.tenants.TenantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReservationController {

    private final ObjectToRentService objectToRentService;
    private final ReservationService reservationService;
    private final TenantService tenantService;

    @PostMapping("/createReservation")
    public ReservationResponseDTO createReservation(@RequestBody ReservationRequestDTO ReservationDTO) {

        // Simple method to check if the reservation's dates are valid.
        reservationService.checkIfDatesAreCorrect(ReservationDTO.getFromDateRent(), ReservationDTO.getToDateRent(), ReservationDTO.getObjectToRentId());

        ReservationResponseDTO reservationResponseDTO = new ReservationResponseDTO();
        // Calculates the price of the reservation.
        BigDecimal howMuchToPay = objectToRentService.calculateAmountToPay(ReservationDTO.getFromDateRent(), ReservationDTO.getToDateRent(), ReservationDTO.getObjectToRentId());

        reservationResponseDTO.setAmountToPay(howMuchToPay);

        // Save the tenant in the database.
        tenantService.convertToEntityAndSave(ReservationDTO.getNameOfTenant());

        // Save the reservation in the database.
        reservationService.convertToEntityAndSave(ReservationDTO, howMuchToPay);

        return reservationResponseDTO;
    }

    @PutMapping("/updateReservation")
    public ReservationResponseDTO updateReservation(@RequestBody UpdateReservationDTO updateReservationDTO) {

        reservationService.checkIfDatesAreCorrect(updateReservationDTO.getFromDateRent(), updateReservationDTO.getToDateRent(), updateReservationDTO.getReservationId());

        Reservation reservationFromDB = reservationService.findReservationById(updateReservationDTO.getReservationId());

        ReservationResponseDTO reservationResponseDTO = new ReservationResponseDTO();

        // Calculates the price of the reservationFromDB.
        BigDecimal howMuchToPay = objectToRentService.calculateAmountToPay(updateReservationDTO.getFromDateRent(), updateReservationDTO.getToDateRent(), reservationFromDB.getObjectToRent().getId());

        // Calculates the difference in price between old and new reservations, returns negative number if there's some money to return to the tenant.
        reservationResponseDTO.setAmountToPay(howMuchToPay.subtract(reservationFromDB.getRentCost()));

        reservationService.updateEntityAndSave(reservationFromDB, updateReservationDTO, howMuchToPay);

        return reservationResponseDTO;
    }

    @GetMapping("/tenantsReservations/{tenantName}")
    public List<ReservationDTO> findAllReservationsByTenantId(@PathVariable String tenantName) {
        return reservationService.findAllReservationsByTenantName(tenantName);
    }

    @GetMapping("objectsReservations/{objectId}")
    public List<ReservationDTO> findAllReservationsByObjectId(@PathVariable Long objectId) {
        return reservationService.findAllReservationsByObjectId(objectId);
    }
}
