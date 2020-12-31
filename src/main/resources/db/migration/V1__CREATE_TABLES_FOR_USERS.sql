create table users(
    id serial primary key,
    user_id varchar(30),
    title varchar(30),
    email varchar(250),
    firstname varchar(30),
    lastname varchar(30),
    start_date varchar(30),
    end_date varchar(30),
    encoded_password varchar(100),
    role varchar default 'employee'
);