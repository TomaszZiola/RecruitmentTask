package com.ziola.recruitmenttask.objectstorent;

import com.ziola.recruitmenttask.landlords.Landlord;
import com.ziola.recruitmenttask.reservations.Reservation;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "objects_to_rent")
public class ObjectToRent {

    @Id
    @SequenceGenerator(name = "objects_to_rent_id_seq", sequenceName = "objects_to_rent_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "objects_to_rent_id_seq")
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
    @ToString.Exclude
    private List<Reservation> reservations;

    @ManyToOne
    @JoinColumn(name = "landlord_id")
    private Landlord landlord;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ObjectToRent that = (ObjectToRent) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
