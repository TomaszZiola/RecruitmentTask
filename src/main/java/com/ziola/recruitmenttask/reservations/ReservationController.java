package com.ziola.recruitmenttask.reservations;

import com.ziola.recruitmenttask.objectstorent.ObjectToRentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReservationController {

    private final ObjectToRentService objectToRentService;
    private final ReservationService reservationService;

    @PostMapping("/createReservation")
    public ReservationResponseDTO createReservation(@RequestBody ReservationRequestDTO reservationRequestDTO) {

        return reservationService.takeAndProceedDTO(reservationRequestDTO);
    }

    @PutMapping("/updateReservation")
    public ReservationResponseDTO updateReservation(@RequestBody UpdateReservationDTO updateReservationDTO) {

        return reservationService.takeDTOUpdateReservation(updateReservationDTO);
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