create table if not exists school
(
    id      int unique,
    address varchar(255) not null
);

create table if not exists subject
(
    id   int unique,
    name varchar(255) not null
);

create table if not exists teacher
(
    id        int unique,
    full_name varchar(255) not null,
    age       int          not null,
    sex       varchar(10)  not null,
    school_id int          not null,
    foreign key (school_id) references school (id)
);


create table if not exists student
(
    id        int unique,
    full_name varchar(255) not null,
    Age       int          not null,
    Sex       varchar(10)  not null,
    school_id int          not null,
    foreign key (school_id) references school (id)
);

create table if not exists teacher_subjects
(
    teacher_id int not null,
    subject_id int not null,
    foreign key (teacher_id) references teacher (id),
    foreign key (subject_id) references subject (id),
    primary key (teacher_id, subject_id)
);

create table if not exists student_subjects
(
    subject_id int not null,
    student_id int not null,
    foreign key (subject_id) references subject (id),
    foreign key (student_id) references student (id),
    primary key (student_id, subject_id)
);