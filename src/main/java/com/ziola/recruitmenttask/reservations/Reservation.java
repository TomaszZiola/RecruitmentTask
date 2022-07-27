package com.ziola.recruitmenttask.reservations;

import com.ziola.recruitmenttask.landlords.Landlord;
import com.ziola.recruitmenttask.objectstorent.ObjectToRent;
import com.ziola.recruitmenttask.tenants.Tenant;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private Long id;

    @Column(name = "rent_cost")
    private BigDecimal rentCost;

    @Column(name = "from_date_rent")
    private LocalDate fromDateRent;

    @Column(name = "to_date_rent")
    private LocalDate toDateRent;

    @ManyToOne
    @JoinColumn(name = "tenant_id")
    private Tenant tenant;

    @ManyToOne
    @JoinColumn(name = "landlord_id")
    private Landlord landlord;

    @ManyToOne
    @JoinColumn(name = "object_to_rent_id")
    private ObjectToRent objectToRent;

    Reservation(Long id, BigDecimal rentCost, LocalDate fromDateRent, LocalDate toDateRent, Tenant tenant, Landlord landlord, ObjectToRent objectToRent) {
        this.id = id;
        this.rentCost = rentCost;
        this.fromDateRent = fromDateRent;
        this.toDateRent = toDateRent;
        this.tenant = tenant;
        this.landlord = landlord;
        this.objectToRent = objectToRent;
    }

    public static ReservationBuilder builder() {
        return new ReservationBuilder();
    }

    public static class ReservationBuilder {
        private Long id;
        private BigDecimal rentCost;
        private LocalDate fromDateRent;
        private LocalDate toDateRent;
        private Tenant tenant;
        private Landlord landlord;
        private ObjectToRent objectToRent;

        ReservationBuilder() {
        }

        public ReservationBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public ReservationBuilder rentCost(BigDecimal rentCost) {
            this.rentCost = rentCost;
            return this;
        }

        public ReservationBuilder fromDateRent(LocalDate fromDateRent) {
            this.fromDateRent = fromDateRent;
            return this;
        }

        public ReservationBuilder toDateRent(LocalDate toDateRent) {
            this.toDateRent = toDateRent;
            return this;
        }

        public ReservationBuilder tenant(Tenant tenant) {
            this.tenant = tenant;
            return this;
        }

        public ReservationBuilder landlord(Landlord landlord) {
            this.landlord = landlord;
            return this;
        }

        public ReservationBuilder objectToRent(ObjectToRent objectToRent) {
            this.objectToRent = objectToRent;
            return this;
        }

        public Reservation build() {
            return new Reservation(id, rentCost, fromDateRent, toDateRent, tenant, landlord, objectToRent);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Reservation that = (Reservation) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
