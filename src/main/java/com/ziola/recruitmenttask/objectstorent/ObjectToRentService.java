package com.ziola.recruitmenttask.objectstorent;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface ObjectToRentService {

    BigDecimal calculateAmountToPay(LocalDate start, LocalDate end, Long objectToRentId);
}
