create table if not exists "School"
(
    id        int unique,
    "Address" varchar(255) not null
);

create table if not exists "Subject"
(
    id     int unique,
    "Name" varchar(255) not null
);

create table if not exists "Teacher"
(
    id          int unique,
    "Full name" varchar(255) not null,
    "Age"       int          not null,
    "Sex"       varchar(10)  not null,
    school_id   int          not null,
    foreign key (school_id) references "School" (id)
);


create table if not exists "Student"
(
    id          int unique,
    "Full name" varchar(255) not null,
    "Age"       int          not null,
    "Sex"       varchar(10)  not null,
    school_id   int          not null,
    foreign key (school_id) references "School" (id)
);

create table if not exists "TeacherSubjects"
(
    teacher_id int not null,
    subject_id int not null,
    foreign key (teacher_id) references "Teacher" (id),
    foreign key (subject_id) references "Subject" (id)
);

create table if not exists "TeacherStudent"
(
    teacher_id int not null,
    student_id int not null,
    foreign key (teacher_id) references "Teacher" (id),
    foreign key (student_id) references "Student" (id)
);