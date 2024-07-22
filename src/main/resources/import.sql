INSERT INTO "customer"(name,last_name, phone_number,address, identification) values ('Dailenys','Quintana Montes de Oca','+5355511109','Souberville #559A/Calzada y Coronel V.','00011823456');
INSERT INTO "customer"(name,last_name, phone_number,address, identification) values ('Minelia',' Montes de Oca de Armas','+5353690037','Souberville #559A/Calzada y Coronel V.','67012623457');
/* arrangement types*/
insert into "arrangement_type"(price, description) VALUES (25.00,'rubber base');
insert into "arrangement_type"(price, description) VALUES (45.00,'acrygel');
insert into "arrangement_type"(price, description) VALUES (40.00,'short acrylics nails');
insert into "arrangement_type"(price, description) VALUES (50.00,'medium acrylic nails');
insert into "arrangement_type"(price, description) VALUES (60.00,'large acrylic nails');
insert into "arrangement_type"(price, description) VALUES (35.00,'acrylic nail refill');
insert into "arrangement_type"(price, description) VALUES (20.00,'gel polish in a natural nail');
insert into "arrangement_type"(price, description) VALUES (25.00,'pedicure');


/*schedule_tasks*/
insert into "scheduler_task"(hour_end, hour_start, appointment_date) VALUES ('11:00','09:00','2023-12-04');

insert into "scheduler_customer"( customer_id,scheduler_id) VALUES (1,1);
insert into "scheduler_customer"( customer_id,scheduler_id) VALUES (2,1);

insert into "scheduler_arrangement"(arrangement_id, scheduler_id) VALUES (1,1);
insert into "scheduler_arrangement"(arrangement_id, scheduler_id) VALUES (3,1);