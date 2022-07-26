package com.ziola.recruitmenttask.tenants;

import com.ziola.recruitmenttask.reservations.Reservation;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tenant")
public class Tenant {

    @Id
    @SequenceGenerator(name = "tenant_id_seq", sequenceName = "tenant_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tenant_id_seq")
    @Column(name = "tenant_id")
    private Long id;

    @Column(name = "tenant_name")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tenant")
    @ToString.Exclude
    List<Reservation> reservations;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Tenant tenant = (Tenant) o;
        return id != null && Objects.equals(id, tenant.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
