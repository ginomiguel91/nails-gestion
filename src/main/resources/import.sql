INSERT INTO "customer"(name,last_name, phone_number,address, identification) values ('Dailenys','Quintana Montes de Oca','+5355511109','Souberville #559A/Calzada y Coronel V.','00011823456');
INSERT INTO "customer"(name,last_name, phone_number,address, identification) values ('Minelia',' Montes de Oca de Armas','+5353690037','Souberville #559A/Calzada y Coronel V.','67012623457');
/* arrangement types*/
insert into "arrangement_type"(price, description) VALUES (2.50,'nail leveling with rubber base');
insert into "arrangement_type"(price, description) VALUES (3.00,'construction gel on natural nail');
insert into "arrangement_type"(price, description) VALUES (3.77,'polygel on natural nail depending on length');
insert into "arrangement_type"(price, description) VALUES (5.66,'polygel with tip or mold');
insert into "arrangement_type"(price, description) VALUES (3.40,'polygel filling');
insert into "arrangement_type"(price, description) VALUES (5.66,'short acrylic nails');
insert into "arrangement_type"(price, description) VALUES (7.55,'medium acrylic nails');
insert into "arrangement_type"(price, description) VALUES (9.43,'large acrylic nails');
insert into "arrangement_type"(price, description) VALUES (3.77,'acrylic nail filler');
insert into "arrangement_type"(price, description) VALUES (3.77,'acrylic nail filler');
insert into "arrangement_type"(price, description) VALUES (1.37,'gel polish in a natural nail');
insert into "arrangement_type"(price, description) VALUES (1.13,'gel polish on feet');
insert into "arrangement_type"(price, description) VALUES (1.88,'acrylic toenails(all nails)');

/*schedule_tasks*/
insert into "scheduler_task"(hour_end, hour_start, appointment_date) VALUES ('11:00','09:00','2023-12-04');

insert into "scheduler_customer"( customer_id,scheduler_id) VALUES (1,1);
insert into "scheduler_customer"( customer_id,scheduler_id) VALUES (2,1);

insert into "scheduler_arrangement"(arrangement_id, scheduler_id) VALUES (1,1);
insert into "scheduler_arrangement"(arrangement_id, scheduler_id) VALUES (3,1);