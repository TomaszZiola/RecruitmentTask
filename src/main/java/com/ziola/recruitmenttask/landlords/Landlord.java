package com.ziola.recruitmenttask.landlords;

import com.ziola.recruitmenttask.objectstorent.ObjectToRent;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "landlord")
public class Landlord {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "landlord_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "landlord")
    @ToString.Exclude
    private List<ObjectToRent> objectToRent;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Landlord landlord = (Landlord) o;
        return id != null && Objects.equals(id, landlord.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
