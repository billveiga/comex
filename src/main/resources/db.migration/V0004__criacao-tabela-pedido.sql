create table pedido (
       id bigint not null auto_increment,
        data date,
        desconto decimal(19,2),
        tipo_desconto varchar(255),
        cliente_id bigint not null,
        primary key (id)
);