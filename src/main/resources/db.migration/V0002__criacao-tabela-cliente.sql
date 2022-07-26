create table cliente (
        id bigint not null auto_increment,
        cpf bigint not null,
        bairro varchar(255) not null,
        cidade varchar(255) not null,
        complemento varchar(255),
        estado varchar(255) not null,
        numero varchar(255) not null,
        rua varchar(255) not null,
        nome varchar(255) not null,
        telefone varchar(255),
        primary key (id)
);