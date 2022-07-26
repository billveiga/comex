create table produto (
       id bigint not null auto_increment,
        descricao varchar(255),
        nome varchar(255) not null,
        preco_unitario decimal(19,2) not null,
        quantidade_estoque integer not null,
        categoria_id bigint not null,
        primary key (id)
    );