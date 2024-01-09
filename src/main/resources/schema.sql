create table if not exists authors (
    author_id int primary key,
    author_name varchar(40) not null,
    age int
);

create table if not exists books (
    id int primary key,
    title varchar(30) not null,
    ganre varchar(15),
    author_id int,
    constraint fk_author foreign key (author_id) references authors(author_id) on delete cascade
);
