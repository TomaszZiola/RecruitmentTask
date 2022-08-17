package com.ziola.recruitmenttask.objectstorent;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ObjectToRentServiceImpl implements ObjectToRentService {

    private final ObjectToRentDAO objectToRentDAO;

    @Override
    public BigDecimal calculateAmountToPay(LocalDate start, LocalDate end, Long objectToRentId) {
        ObjectToRent objectToRent = objectToRentDAO.findObjectToRentById(objectToRentId);
        BigDecimal pricePerNight = objectToRent.getPriceForNight();
        int numberOfNights = (int) start.until(end).get(java.time.temporal.ChronoUnit.DAYS);
        return pricePerNight.multiply(new BigDecimal(numberOfNights));
    }
}
