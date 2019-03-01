create table auth_user
(
  id integer(11) not null auto_increment,
  username varchar(255),
  password varchar(255),
  primary key (id)
);

create table role_user
(
  id integer(11) not null auto_increment,
  user_id integer(11),
  username varchar(255),
  role varchar(20),
  primary key (id),
  foreign key (user_id) references auth_user(id)
);

create table user_accounts
(
  id integer(11) not null auto_increment,
  user_id int(11),
  iban varchar(23),
  funds integer(11),
  real_name varchar(255),
  primary key (id),
  foreign key (user_id) references auth_user(id)
);

insert into auth_user (username, password) values ('koks','nema');
insert into auth_user (username, password) values ('marko','nema');
insert into auth_user (username, password) values ('pero','nema');
insert into auth_user (username, password) values ('goran','nema');

insert into role_user (user_id, username, role) values (1,'koks','ADMIN');
insert into role_user (user_id, username, role) values (1,'koks','USER');
insert into role_user (user_id, username, role) values (2,'marko','USER');
insert into role_user (user_id, username, role) values (3,'pero','USER');
insert into role_user (user_id, username, role) values (4,'goran','USER');

insert into user_accounts (user_id, iban, funds, real_name) values (1,'HR8888010051550200001','17500','Koks');
insert into user_accounts (user_id, iban, funds, real_name) values (1,'HR8888010051550200002','500','Koks');
insert into user_accounts (user_id, iban, funds, real_name) values (1,'HR8888010051550200003','2500','Koks');

insert into user_accounts (user_id, iban, funds, real_name) values (2,'HR8888010051550200004','17500','Marko');
insert into user_accounts (user_id, iban, funds, real_name) values (2,'HR8888010051550200005','500','Marko');

insert into user_accounts (user_id, iban, funds, real_name) values (3,'HR8888010051550200006','1500','Pero');
insert into user_accounts (user_id, iban, funds, real_name) values (3,'HR8888010051550200007','500','Pero');

insert into user_accounts (user_id, iban, funds, real_name) values (4,'HR8888010051550200008','2000','Goran');