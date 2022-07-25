Solution of the recruitment task. To run the repository, you need to download it and extract it to your hard drive. The program uses the H2 memory base.

To add a reservation, please send JSON to the following address:

/ createReservation

JSON should have the following structure:

    {
     "nameOfTenant" : "name",
     "fromDateRent" : "YYYY-MM-DD",
     "toDateRent" : "YYYY-MM-DD",
     "objectToRentId" : "object_id"
    }

To change the booking, please send JSON to the following address:
/ updateReservation

JSON should be of the form:

    {
     "reservationId": "reservation_id",
     "fromDateRent": "2023-12-06",
     "toDateRent": "2023-12-07",
    }
    
    To obtain a list of reservations broken down by the name of the tenant, please send JSON to the following address:
/ tenantsReservations / {tenantName}

where tenantName is the tenant's last name.

To obtain a list of reservations broken down by a given object for rent, please send JSON to the following address:
/ objectsReservations / {objectId}

where objectId is the identification number of the object in the database.
