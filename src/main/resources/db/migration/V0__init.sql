create table school
(
    id      serial primary key,
    number  int,
    name    varchar,
    address varchar
);

create table "user"
(
    id         serial primary key,
    first_name varchar,
    last_name  varchar,
    age        int,
    sex        varchar,
    school_id  int not null,
    foreign key (school_id) references school (id) on delete set null
);

create table friends
(
    user1_id int not null,
    user2_id int not null,
    foreign key (user1_id) references "user" (id) on delete cascade,
    foreign key (user2_id) references "user" (id) on delete cascade,
    primary key (user1_id, user2_id)
);

create table post
(
    id      serial,
    body    varchar not null,
    user_id int     not null,
    foreign key (user_id) references "user" (id) on delete cascade
);



