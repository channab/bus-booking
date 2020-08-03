INSERT INTO OPERATOR (id, name) VALUES (1, 'Classic Travels');
INSERT INTO OPERATOR (id , name) VALUES (2, 'ABC Travels');
INSERT INTO OPERATOR (id, name) VALUES (3, 'SG Travels');


INSERT INTO BUS (id, number, operator_id) VALUES (1, 'ABC-123', 1);
INSERT INTO BUS (id, number, operator_id) VALUES (2, 'CDE-567', 1);
INSERT INTO BUS (id, number, operator_id) VALUES (3, 'TST-557', 2);
INSERT INTO BUS (id, number, operator_id) VALUES (4, 'KKK-967', 3);
INSERT INTO BUS (id, number, operator_id) VALUES (5, 'LMN-123', 3);

INSERT INTO SEAT (id, number, bus_id, reservation_status) VALUES (1, 'A-1', 1, 'OPEN');
INSERT INTO SEAT (id, number, bus_id, reservation_status) VALUES (2, 'A-2', 1, 'OPEN');
INSERT INTO SEAT (id, number, bus_id, reservation_status) VALUES (3, 'A-3', 1, 'OPEN');
INSERT INTO SEAT (id, number, bus_id, reservation_status) VALUES (4, 'B-1', 2, 'OPEN');
INSERT INTO SEAT (id, number, bus_id, reservation_status) VALUES (5, 'B-2', 2, 'OPEN');

INSERT INTO CUSTOMER (id, name) VALUES (1, 'Channa');
INSERT INTO CUSTOMER (id , name) VALUES (2, 'Alex');
INSERT INTO CUSTOMER (id, name) VALUES (3, 'Mark');


INSERT INTO CITY (id, name) VALUES (1, 'Senkang');
INSERT INTO CITY (id , name) VALUES (2, 'Holland Village');
INSERT INTO CITY (id, name) VALUES (3, 'Clementi');
INSERT INTO CITY (id, name) VALUES (4, 'Newton');
INSERT INTO CITY (id, name) VALUES (5, 'Woodlands');

INSERT INTO BUS_SCHEDULE (id, bus_id, start_date_time, return_date_time, source_city_id, destination_city_id, schedule_status, price) 
	VALUES (1, 1, {ts '2020-08-16 08:00:00'}, {ts '2020-08-17 08:00:00'}, 1, 2, 'OPEN', 20.00);
INSERT INTO BUS_SCHEDULE (id, bus_id, start_date_time, return_date_time, source_city_id, destination_city_id, schedule_status, price) 
	VALUES (2, 2, {ts '2020-08-16 12:00:00'}, {ts '2020-08-17 13:00:00'}, 1, 2, 'OPEN', 15.50);
INSERT INTO BUS_SCHEDULE (id, bus_id, start_date_time, return_date_time, source_city_id, destination_city_id, schedule_status, price) 
	VALUES (3, 3, {ts '2020-08-16 13:00:00'}, {ts '2020-08-18 13:00:00'}, 5, 4, 'OPEN', 12.00);
INSERT INTO BUS_SCHEDULE (id, bus_id, start_date_time, return_date_time, source_city_id, destination_city_id, schedule_status, price) 
	VALUES (4, 4, {ts '2020-08-16 07:00:00'}, {ts '2020-08-19 13:00:00'}, 4, 2, 'OPEN', 13.99);
INSERT INTO BUS_SCHEDULE (id, bus_id, start_date_time, return_date_time, source_city_id, destination_city_id, schedule_status, price) 
	VALUES (5, 5, {ts '2020-08-16 17:00:00'}, {ts '2020-08-20 13:00:00'}, 1, 5, 'OPEN', 25.00);



