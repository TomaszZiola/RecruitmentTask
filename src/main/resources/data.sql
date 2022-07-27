INSERT INTO landlord (landlord_id, name) VALUES (1, 'Robert Lewandowski');
INSERT INTO landlord (landlord_id, name) VALUES (2, 'Jan Kowalski');
INSERT INTO landlord (landlord_id, name) VALUES (3, 'Jan Nowak');

INSERT INTO objects_to_rent (object_to_rent_id, name, description, area, price_for_night, landlord_id) VALUES (1, 'Hotel Katowice1', 'Hotel do wynajęcia 1', '10.00', '100.00', 1);
INSERT INTO objects_to_rent (object_to_rent_id, name, description, area, price_for_night, landlord_id) VALUES (2, 'Hotel Katowice2', 'Hotel do wynajęcia 2!', '20.00', '120.00', 1);
INSERT INTO objects_to_rent (object_to_rent_id, name, description, area, price_for_night, landlord_id) VALUES (3, 'Hotel Katowice3', 'Hotel do wynajęcia 3!', '30.00', '140.00', 2);
INSERT INTO objects_to_rent (object_to_rent_id, name, description, area, price_for_night, landlord_id) VALUES (4, 'Hotel Katowice4', 'Hotel do wynajęcia 4!', '40.00', '160.00', 3);
INSERT INTO objects_to_rent (object_to_rent_id, name, description, area, price_for_night, landlord_id) VALUES (5, 'Hotel Katowice5', 'Hotel do wynajęcia 5!', '50.00', '180.00', 1);
INSERT INTO objects_to_rent (object_to_rent_id, name, description, area, price_for_night, landlord_id) VALUES (6, 'Hotel Katowice6', 'Hotel do wynajęcia 6!', '66.00', '200.00', 2);

INSERT INTO tenant ( tenant_name) VALUES ( 'Tomasz Karolak');
INSERT INTO tenant ( tenant_name) VALUES ( 'Ania Dąbrowska');
INSERT INTO tenant ( tenant_name) VALUES ( 'Zuzanna Kocur');
INSERT INTO tenant ( tenant_name) VALUES ( 'Łukasz Szymański');

INSERT INTO reservations ( object_to_rent_id, tenant_id, landlord_id, rent_cost, from_date_rent, to_date_rent) VALUES ( 1, 1, 1,'140', '2022-08-01', '2022-08-15');
INSERT INTO reservations ( object_to_rent_id, tenant_id, landlord_id, rent_cost, from_date_rent, to_date_rent) VALUES ( 1, 1, 1,'180', '2022-08-16', '2022-08-25');
INSERT INTO reservations ( object_to_rent_id, tenant_id, landlord_id, rent_cost, from_date_rent, to_date_rent) VALUES ( 2, 2, 1,'200', '2022-08-01', '2022-08-03');
INSERT INTO reservations ( object_to_rent_id, tenant_id, landlord_id, rent_cost, from_date_rent, to_date_rent) VALUES ( 3, 3, 2,'100', '2022-08-01', '2022-08-15');
INSERT INTO reservations ( object_to_rent_id, tenant_id, landlord_id, rent_cost, from_date_rent, to_date_rent) VALUES ( 4, 4, 3,'110', '2022-08-01', '2022-08-08');

