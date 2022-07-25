package com.ziola.recruitmenttask.objectstorent;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class ObjectToRentDAOImpl implements ObjectToRentDAO {

    private final EntityManager entityManager;
    @Override
    public ObjectToRent findObjectToRentById(Long objectToRentId) {
        ObjectToRent objectToRentFromDB = entityManager.find(ObjectToRent.class, objectToRentId);
        return objectToRentFromDB;
    }
}

