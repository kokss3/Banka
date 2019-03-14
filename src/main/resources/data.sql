create table user_auth
(
  user_id integer(11) not null auto_increment,
  username varchar(255),
  password varchar(255),
  primary key (user_id)
);

create table user_accounts
(
  user_id integer(11) not null auto_increment,
  user_id int(11),
  iban varchar(23),
  funds integer(11),
  real_name varchar(255),
  primary key (user_id),
  foreign key (user_id) references user_auth(user_id)
);

create table role
(
  user_id integer(11) not null auto_increment,
  roles varchar(20),
  primary key (user_id)
);

create table user_roles
(
  user_id integer(11),
  role_id integer(11),
  foreign key (role_id) references role (user_id),
  foreign key (user_id) references user_auth(user_id),
  primary key (user_id, role_id)
);

insert into user_auth (username, password) values
  ('koks','$2a$10$XlVJzFwouDvTgmu2548xo.8MUsqOmvnhZmnYntJE5WKWC2Wdj1Mbu'),
  ('marko','$2a$10$XlVJzFwouDvTgmu2548xo.8MUsqOmvnhZmnYntJE5WKWC2Wdj1Mbu'),
  ('pero','$2a$10$XlVJzFwouDvTgmu2548xo.8MUsqOmvnhZmnYntJE5WKWC2Wdj1Mbu'),
  ('goran','$2a$10$XlVJzFwouDvTgmu2548xo.8MUsqOmvnhZmnYntJE5WKWC2Wdj1Mbu');

insert into user_accounts (user_id, iban, funds, real_name) values
  (1,'HR8888010051550200001','17500','Koks'),
  (1,'HR8888010051550200002','500','Koks'),
  (1,'HR8888010051550200003','2500','Koks'),
  (2,'HR8888010051550200004','17500','Marko'),
  (2,'HR8888010051550200005','500','Marko'),
  (3,'HR8888010051550200006','1500','Pero'),
  (3,'HR8888010051550200007','500','Pero'),
  (4,'HR8888010051550200008','2000','Goran');

insert into role (roles) values
('ROLE_ADMIN'),
('ROLE_USER');


insert into user_roles (user_id, role_id) values
(1,1),
(1,2),
(2,2),
(3,2),
(4,2);
