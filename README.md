# AttaSystems


CREATE TABLE place (
ID  SERIAL PRIMARY KEY,
name varchar(300)
);


CREATE TABLE sport (
ID   SERIAL PRIMARY KEY,
sport_name varchar(300),
start_month varchar(300),
end_month varchar(300),
price_per_day decimal,
place_id integer,
constraint fk_place foreign key (place_id) references place(id)
);


docker run --name attasystem -e POSTGRES_PASSWORD=admin -d -p 5432:5432 postgres
