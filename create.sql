drop schema cs5200_A3;
create schema cs5200_A3;

CREATE TABLE `cs5200_A3`.`person` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `firstName` VARCHAR(45) NOT NULL,
  `lastName` VARCHAR(45) NOT NULL,
  `username` VARCHAR(45) NOT NULL UNIQUE,
  `password` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL UNIQUE,
  `dob` DATE NOT NULL,
  PRIMARY KEY (`id`));


create table cs5200_A3.developer (
developer_key varchar(45) not null unique,
id int not null,
constraint developer_person_generalization foreign key(id) references person(id)
on delete cascade
);


create table cs5200_A3.user (
user_agreement boolean not null,
id int not null,
constraint user_person_generalization foreign key(id) references person(id)
on delete cascade
);




create table cs5200_A3.phone (
phone varchar(45) not null unique,
is_primary boolean not null,
id int not null,
primary key(id, phone, is_primary),
constraint phone_person_composition foreign key(id) references person(id)
on delete cascade
);


create table cs5200_A3.address (
street1 varchar(45) not null,
street2 varchar(45) default '',
city varchar(45) not null,
state varchar(45) not null default '',
zip varchar(45) not null,
is_primary boolean not null,
id int not null,
primary key(id, street1, city, state, zip, is_primary),
constraint address_person_composition foreign key(id) references person(id)
on delete cascade
);



drop table if exists cs5200_A3.website;
create table cs5200_A3.website (
id int not null,
name varchar(45) not null,
description varchar(225) not null,
created datetime default current_timestamp,
updated datetime default current_timestamp on update current_timestamp,
visits int not null,
developerid int not null,

primary key(id, developerid),
foreign key(developerid) references developer(id)
);


drop table if exists cs5200_A3.page;
create table cs5200_A3.page(
id int not null auto_increment,
title varchar(45) not null unique,
description varchar(225) not null,
created datetime not null default current_timestamp,
updated datetime default current_timestamp on update current_timestamp,
views int not null,
website_id int not null ,
primary key(id),
constraint page_widget_fk foreign key(website_id) references website(id)
on delete cascade
);


drop table if exists cs5200_A3.role;
create table cs5200_A3.role(
id int,
name varchar(45) not null,
primary key(name,id)
);
insert into cs5200_A3.role values(1, 'owner');
insert into cs5200_A3.role values(2, 'admin');
insert into cs5200_A3.role values(3, 'writer');
insert into cs5200_A3.role values(4, 'editor');
insert into cs5200_A3.role values(5, 'reviewer');


drop table if exists cs5200_A3.privilege;
create table cs5200_A3.privilege(
name varchar(45) not null unique default '',
primary key(name)
);

insert into cs5200_A3.privilege values('create');
insert into cs5200_A3.privilege values('read');
insert into cs5200_A3.privilege values('update');
insert into cs5200_A3.privilege values('delete');


drop table if exists cs5200_A3.dtype;
create table cs5200_A3.dtype(
widget_type varchar(50),
primary key(widget_type)
); 

insert into cs5200_A3.dtype values('HEADING');
insert into cs5200_A3.dtype values ('HTML');
insert into cs5200_A3.dtype values ('IMAGE');
insert into cs5200_A3.dtype values ('YOUTUBE');


drop table if exists cs5200_A3.widget;
create table cs5200_A3.widget (
id int not null auto_increment,
name varchar(100) not null unique,
dtype varchar(100) not null,
width int,
height int,
css_Class varchar(100),
css_Style varchar(100),
text varchar(255),
url varchar(255),
sharable boolean,
expandable boolean,
src varchar(255),
size int default 2,
widget_order int not null,
page_id int not null,

primary key(id),
constraint widget_dtype_fk foreign key(dtype) references dtype(widget_type) on delete cascade, 
constraint widget_page_fk foreign key(page_id) references page(id) on delete cascade
);


drop table if exists cs5200_A3.website_role;
create table cs5200_A3.website_role(
website_id int not null,
developer_id int not null,
role varchar(45) not null,
primary key(website_id, developer_id, role),
foreign key(website_id) references website(id)
on delete cascade,
foreign key(developer_id) references developer(id)
on delete cascade,
foreign key(role) references role(name) 
on delete cascade
);

drop table if exists cs5200_A3.website_privilege;
create table cs5200_A3.website_privilege(
website_id int not null,
developer_id int not null,
privilege varchar(45) not null,
primary key(website_id, developer_id, privilege),
foreign key(website_id) references website(id)
on delete cascade,
foreign key(developer_id) references developer(id)
on delete cascade,
foreign key(privilege) references privilege(name) 
on delete cascade
);


drop table if exists cs5200_A3.page_role;
create table cs5200_A3.page_role(
page_id int not null,
developer_id int not null,
role varchar(45) not null,
primary key(page_id, developer_id, role),
foreign key(page_id) references page(id) on delete cascade,
foreign key(developer_id) references developer(id) on delete cascade,
foreign key(role) references role(name) on delete cascade
);


drop table if exists cs5200_A3.page_privilege;
create table cs5200_A3.page_privilege(
page_id int not null,
developer_id int not null,
privilege varchar(45) not null,
primary key(page_id, developer_id, privilege),
foreign key(page_id) references page(id) on delete cascade,
foreign key(developer_id) references developer(id) on delete cascade,
foreign key(privilege) references privilege(name) on delete cascade
);