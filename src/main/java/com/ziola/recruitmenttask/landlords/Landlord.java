package com.ziola.recruitmenttask.landlords;

import com.ziola.recruitmenttask.objectstorent.ObjectToRent;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "landlord")
public class Landlord {

    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    @Column(name = "landlord_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "landlord")
    private List<ObjectToRent> objectToRent;
}
