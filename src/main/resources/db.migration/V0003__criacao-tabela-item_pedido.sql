create table item_pedido (
       id bigint not null auto_increment,
        desconto decimal(19,2) not null,
        preco_unitario decimal(19,2) not null,
        quantidade integer not null,
        tipo_desconto varchar(255),
        pedido_id bigint not null,
        produto_id bigint not null,
        primary key (id)
);
