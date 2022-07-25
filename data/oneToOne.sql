create table person(
    id serial primary key,
    name varchar(255)
);

create table tabel(
    id serial primary key,
    number int
);

create table person_tabel(
    id serial primary key,
    person_id int references person(id) unique,
    tabel_id int references tadel(id) unique
);