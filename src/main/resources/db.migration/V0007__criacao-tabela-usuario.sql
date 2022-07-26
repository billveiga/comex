create table usuario (
       id bigint not null auto_increment,
        email varchar(255) not null,
        senha varchar(255) not null,
        cliente_id bigint not null,
        primary key (id)
);