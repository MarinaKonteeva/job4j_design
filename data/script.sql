create table students(
    id serial primary key,
    name varchar (255),
    faculty text,
    age integer
);

insert into students(name, faculty, age) values('Petr', 'history', 25);

select * from students;

update students set name = 'Ivan';

select * from students;

delete from students;

select * from students;