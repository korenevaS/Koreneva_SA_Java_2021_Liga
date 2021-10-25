create table "role"
(
    id   serial primary key,
    name varchar
);

insert into "role"(name)
values ('ROLE_ADMIN');
insert into "role"(name)
values ('ROLE_USER');

create table "user"
(
    id         serial primary key,
    first_name varchar,
    last_name  varchar,
    username   varchar,
    password   varchar,
    role_id    int,
    foreign key (role_id) references "role" (id)
);

create table receipt
(
    id                            serial primary key,
    user_id                       int,
    start_of_reception            timestamp,
    creation_date                 timestamp,
    confirmation                  boolean default null,
    was_used                      boolean default false,
    link_waiting_for_confirmation uuid,
    foreign key (user_id) references "user" (id)
);

