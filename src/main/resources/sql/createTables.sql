create table CAR (
    id serial primary key,
    color varchar(20) not null,
    body_type varchar(20) not null,
    manufacturer varchar(20) not null,
    gear_type varchar(15) not null,
    fuel_type varchar(15) not null,
    release_date date not null,
    description varchar(500)
)