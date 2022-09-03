create table films(
     id serial primary key,
     name varchar(255)
 );

 create table actors(
     id serial primary key,
     name varchar(255)
 );

 create table films_actors(
     id serial primary key,
     film_id int references films(id),
     actor_id int references actors(id)
 );

 insert into films(name) values ('Terminator');
 insert into films(name) values ('Alien');

 insert into actors(name) values ('Sigourney');
 insert into actors(name) values ('Arnold');

 insert into films_actors (film_id, actor_id) values (1,2);
 insert into films_actors (film_id, actor_id) values (2,1);

 select films.name, actors.name from films, actors, films_actors
 where films.id = films_actors.film_id and actors.id = films_actors.actor_id;