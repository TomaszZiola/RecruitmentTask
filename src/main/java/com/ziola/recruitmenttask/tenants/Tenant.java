package com.ziola.recruitmenttask.tenants;

import com.ziola.recruitmenttask.reservations.Reservation;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tenant")
public class Tenant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tenant_id")
    private Long id;

    @Column(name = "tenant_name")
    private String name;

    @OneToMany(mappedBy = "tenant")
    @ToString.Exclude
    List<Reservation> reservations;
}
