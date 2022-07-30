package com.ziola.recruitmenttask.landlords;

import com.ziola.recruitmenttask.objectstorent.ObjectToRent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

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
}
