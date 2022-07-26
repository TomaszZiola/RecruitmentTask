package com.ziola.recruitmenttask.reservations;

import com.ziola.recruitmenttask.errors.NoReservationFoundException;
import com.ziola.recruitmenttask.errors.ObjectToRentIncorrectDateException;
import com.ziola.recruitmenttask.objectstorent.ObjectToRent;
import com.ziola.recruitmenttask.objectstorent.ObjectToRentDAO;
import com.ziola.recruitmenttask.objectstorent.ObjectToRentService;
import com.ziola.recruitmenttask.tenants.Tenant;
import com.ziola.recruitmenttask.tenants.TenantDAO;
import com.ziola.recruitmenttask.tenants.TenantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ReservationServiceImpl implements ReservationService {

    private final ObjectToRentDAO objectToRentDAO;
    private final TenantDAO tenantDAO;
    private final ReservationDAO reservationDAO;
    private final ConverterReservationEntityIntoDTO converterReservationEntityIntoDTO;
    private final ObjectToRentService objectToRentService;
    private final TenantService tenantService;

    @Override
    public ReservationResponseDTO takeAndProceedDTO(ReservationRequestDTO reservationRequestDTO) {

        ReservationResponseDTO reservationResponseDTO = new ReservationResponseDTO();
        checkIfDatesAreCorrect(reservationRequestDTO.getFromDateRent(), reservationRequestDTO.getToDateRent(), reservationRequestDTO.getObjectToRentId());
        BigDecimal howMuchToPay = objectToRentService.calculateAmountToPay(reservationRequestDTO.getFromDateRent(), reservationRequestDTO.getToDateRent(), reservationRequestDTO.getObjectToRentId());
        tenantService.convertToEntityAndSave(reservationRequestDTO.getNameOfTenant());
        convertToEntityAndSave(reservationRequestDTO, howMuchToPay);
        reservationResponseDTO.setAmountToPay(howMuchToPay);

        return reservationResponseDTO;
    }

    @Override
    public ReservationResponseDTO takeDTOUpdateReservation(UpdateReservationDTO updateReservationDTO) {

        ReservationResponseDTO reservationResponseDTO = new ReservationResponseDTO();
        Reservation reservationFromDB = findReservationById(updateReservationDTO.getReservationId());
        checkIfDatesAreCorrect(updateReservationDTO.getFromDateRent(), updateReservationDTO.getToDateRent(), updateReservationDTO.getReservationId());
        BigDecimal howMuchToPay = objectToRentService.calculateAmountToPay(updateReservationDTO.getFromDateRent(), updateReservationDTO.getToDateRent(), reservationFromDB.getObjectToRent().getId());
        reservationResponseDTO.setAmountToPay(howMuchToPay.subtract(reservationFromDB.getRentCost()));
        updateEntityAndSave(reservationFromDB, updateReservationDTO, howMuchToPay);

        return reservationResponseDTO;
    }

    @Override
    public void checkIfDatesAreCorrect(LocalDate fromDateRent, LocalDate toDateRent, Long objectToRentId) {

        if (fromDateRent.isAfter(toDateRent)) {
            throw new ObjectToRentIncorrectDateException("From date cannot be after to date");
        }
        if (fromDateRent.isEqual(toDateRent)) {
            throw new ObjectToRentIncorrectDateException("Minimum stay time is one day");
        }
        if (fromDateRent.isBefore(LocalDate.now())) {
            throw new ObjectToRentIncorrectDateException("From date cannot be before current date");
        }
        ObjectToRent objectToRent = objectToRentDAO.findObjectToRentById(objectToRentId);
        for (Reservation reservation : objectToRent.getReservations()) {
            if (reservation.getToDateRent().isAfter(fromDateRent)) {
                throw new ObjectToRentIncorrectDateException("This period of time is already reserved");
            }
        }
    }

    @Override
    public void convertToEntityAndSave(ReservationRequestDTO reservationDTO, BigDecimal howMuchToPay) {

        Reservation reservationToSave = Reservation.builder()
                .fromDateRent(reservationDTO.getFromDateRent())
                .toDateRent(reservationDTO.getToDateRent())
                .rentCost(howMuchToPay)
                .tenant(tenantDAO.findTenantByName(reservationDTO.getNameOfTenant()))
                .objectToRent(objectToRentDAO.findObjectToRentById(reservationDTO.getObjectToRentId()))
                .landlord(objectToRentDAO.findObjectToRentById(reservationDTO.getObjectToRentId()).getLandlord())
                .build();
        reservationDAO.save(reservationToSave);
    }

    @Override
    public Reservation findReservationById(Long reservationId) {

        return reservationDAO.findReservationById(reservationId);
    }

    @Override
    public void updateEntityAndSave(Reservation reservation, UpdateReservationDTO updateReservationDTO, BigDecimal howMuchToPay) {

        reservation.setFromDateRent(updateReservationDTO.getFromDateRent());
        reservation.setToDateRent(updateReservationDTO.getToDateRent());
        reservation.setRentCost(howMuchToPay);
        reservationDAO.save(reservation);
    }

    @Override
    public List<ReservationDTO> findAllReservationsByTenantName(String tenantName) {

        Tenant tenant = tenantDAO.findTenantByName(tenantName);
        List<Reservation> reservations = tenant.getReservations();
        if (reservations == null) {
            throw new NoReservationFoundException("There are no reservations made by this tenant");
        }
        List<ReservationDTO> reservationDTOS = new LinkedList<>();
        for (Reservation reservation : reservations) {
            reservationDTOS.add(converterReservationEntityIntoDTO.convertEntity(reservation));
        }
        return reservationDTOS;
    }

    @Override
    public List<ReservationDTO> findAllReservationsByObjectId(Long objectId) {

        List<Reservation> reservations = reservationDAO.findAllReservationsByObjectId(objectId);
        if (reservations.isEmpty()) {
            throw new NoReservationFoundException("There are no reservations for this object");
        }
        List<ReservationDTO> reservationDTOS = new LinkedList<>();
        for (Reservation reservation : reservations) {
            reservationDTOS.add(converterReservationEntityIntoDTO.convertEntity(reservation));
        }
        return reservationDTOS;
    }
}