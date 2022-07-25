package com.ziola.recruitmenttask.reservations;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ReservationResponseDTO {

    private BigDecimal amountToPay;
}
