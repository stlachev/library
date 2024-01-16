create table if not exists authors (
    author_id int primary key,
    author_name varchar(40) not null,
    age int
);

create table if not exists books (
    id int primary key,
    title varchar(30) not null,
    ganre varchar(15),
    qty int,
    price float(5,2),
    author_id int,
    constraint fk_author foreign key (author_id) references authors(author_id) on delete cascade
);

create table if not exists customers (
    id int primary key,
    customer_name    varchar(40) not null,
    customer_address varchar(40) not null
);

create table if not exists orders (
    id int primary key,
    order_id int not null,
    customer_id int,
    book_id int,
    is_out boolean,
    constraint fk_customer foreign key (customer_id) references customers(id),
    constraint fk_book foreign key (book_id) references books(id)
);

create table if not exists invoice {
    id int primary key,
    order_id int,
    total float(5,2)
};