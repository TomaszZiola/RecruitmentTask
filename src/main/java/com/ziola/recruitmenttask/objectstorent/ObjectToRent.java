package com.ziola.recruitmenttask.objectstorent;

import com.ziola.recruitmenttask.landlords.Landlord;
import com.ziola.recruitmenttask.reservations.Reservation;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "objects_to_rent")
public class ObjectToRent {

    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    @Column(name = "object_to_rent_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "area")
    private double area;

    @Column(name = "price_for_night")
    private BigDecimal priceForNight;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "objectToRent")
    private List<Reservation> reservations;

    @ManyToOne
    @JoinColumn(name = "landlord_id")
    private Landlord landlord;
}
