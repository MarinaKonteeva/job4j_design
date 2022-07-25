create table colors(
     id serial primary key,
     color varchar(255)
 );

 create table cars(
     id serial primary key,
     name varchar(255),
     colors_id int references colors(id)

 );

insert into colors(color) values ('red');
insert into colors(color) values ('green');
insert into colors(color) values ('blue');
insert into cars(name, colors_id) values ('honda', 1);
insert into cars(name, colors_id) values ('kia', 2);

select cars.id, cars.name, colors.color from cars, colors where cars.colors_id = colors.id;