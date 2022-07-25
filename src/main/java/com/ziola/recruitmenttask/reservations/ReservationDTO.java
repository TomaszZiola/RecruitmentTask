package com.ziola.recruitmenttask.reservations;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ReservationDTO {

    private final BigDecimal rentCost;

    private final LocalDate fromDateRent;

    private final LocalDate toDateRent;

    private final String tenantName;

    private final String landlordName;

    private final String objectToRentName;

    public static class Builder {
        private BigDecimal rentCost;

        private LocalDate fromDateRent;

        private LocalDate toDateRent;

        private String tenantName;

        private String landlordName;

        private String objectToRentName;

        public Builder(BigDecimal rentCost, LocalDate fromDateRent, LocalDate toDateRent, String tenantName, String landlordName, String objectToRentName) {
            this.rentCost = rentCost;
            this.fromDateRent = fromDateRent;
            this.toDateRent = toDateRent;
            this.tenantName = tenantName;
            this.landlordName = landlordName;
            this.objectToRentName = objectToRentName;
        }

        public Builder() {

        }
        public Builder rentCost(BigDecimal rentCost) {
            this.rentCost = rentCost;
            return this;
        }
        public Builder fromDateRent(LocalDate fromDateRent) {
            this.fromDateRent = fromDateRent;
            return this;
        }
        public Builder toDateRent(LocalDate toDateRent) {
            this.toDateRent = toDateRent;
            return this;
        }
        public Builder tenantName(String tenantName) {
            this.tenantName = tenantName;
            return this;
        }
        public Builder landlordName(String landlordName) {
            this.landlordName = landlordName;
            return this;
        }
        public Builder objectToRentName(String objectToRentName) {
            this.objectToRentName = objectToRentName;
            return this;
        }
        public ReservationDTO build() {
            return new ReservationDTO(this);
        }
    }
    private ReservationDTO(Builder builder) {
        this.rentCost = builder.rentCost;
        this.fromDateRent = builder.fromDateRent;
        this.toDateRent = builder.toDateRent;
        this.tenantName = builder.tenantName;
        this.landlordName = builder.landlordName;
        this.objectToRentName = builder.objectToRentName;
    }
}

