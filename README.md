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
