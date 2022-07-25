package com.ziola.recruitmenttask.tenants;

import com.ziola.recruitmenttask.reservations.Reservation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tenant")
public class Tenant {

    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    @Column(name = "tenant_id")
    private Long id;

    @Column(name = "tenant_name")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tenant")
    List<Reservation> reservations;
}
