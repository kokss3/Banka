create table auth_user
(
  id integer(11) not null auto_increment,
  username varchar(255),
  password varchar(255),
  primary key (id)
);
-- Relacija između auth_user i role_user nije u redu ako želiš da user ima više rola
-- u ovom slučaju napraviš da svaka rola ima samo jednog usera, što znači ako želiš da 1000 usera ima rolu USER -> morat ćeš imati 1000 istih rola
-- Možeš promijeniti da svaki user ima samo jednu rolu pa promijeniti bazu na temelju toga
-- Ako želiš ostaviti da user ima više rola, moraš imati dodatnu tablicu (user_roles) koja ima user_id i role_id kao primarne ključeve...
create table role_user
(
  id integer(11) not null auto_increment,
  user_id integer(11),
  roles varchar(20),
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

insert into auth_user (username, password) values
  ('koks','$2a$10$XlVJzFwouDvTgmu2548xo.8MUsqOmvnhZmnYntJE5WKWC2Wdj1Mbu'),
  ('marko','$2a$10$XlVJzFwouDvTgmu2548xo.8MUsqOmvnhZmnYntJE5WKWC2Wdj1Mbu'),
  ('pero','$2a$10$XlVJzFwouDvTgmu2548xo.8MUsqOmvnhZmnYntJE5WKWC2Wdj1Mbu'),
  ('goran','$2a$10$XlVJzFwouDvTgmu2548xo.8MUsqOmvnhZmnYntJE5WKWC2Wdj1Mbu');

insert into role_user (user_id, roles) values
  (1,'ADMIN'),
  (1,'USER'),
  (2,'USER'),
  (3,'USER'),
  (4,'USER');

insert into user_accounts (user_id, iban, funds, real_name) values
  (1,'HR8888010051550200001','17500','Koks'),
  (1,'HR8888010051550200002','500','Koks'),
  (1,'HR8888010051550200003','2500','Koks'),
  (2,'HR8888010051550200004','17500','Marko'),
  (2,'HR8888010051550200005','500','Marko'),
  (3,'HR8888010051550200006','1500','Pero'),
  (3,'HR8888010051550200007','500','Pero'),
  (4,'HR8888010051550200008','2000','Goran');