

create table users (
  id                    varchar(255) not null,
  user_name             varchar(30) not null unique,
  password              varchar(80) not null,
  email                 varchar(50) unique,
  role                  varchar(30) not null,
  status                varchar(30) not null,
  primary key (id)
);
