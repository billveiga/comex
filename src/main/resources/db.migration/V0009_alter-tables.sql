alter table usuario
       add constraint UK_suh64qcx0h83ynm45sm2r2lk7 unique (cliente_id);

alter table item_pedido
       add constraint FK42mycompce3b7yt3l6ukdwsxy
       foreign key (pedido_id)
       references pedido (id);

alter table item_pedido
       add constraint FKxytdlekpdaobqphujy9bmuhl
       foreign key (produto_id)
       references produto (id);

alter table pedido
       add constraint FKg7202lk0hwxn04bmdl2thth5b
       foreign key (cliente_id)
       references cliente (id);

alter table produto
       add constraint FK8rqw0ljwdaom34jr2t46bjtrn
       foreign key (categoria_id)
       references categoria (id);

alter table usuario
       add constraint FKdx76w4skuwj8wldmr7ebfyegq
       foreign key (cliente_id)
       references cliente (id);

alter table usuario_perfil
       add constraint FKebaxerhtjtge268fwjphj1kpt
       foreign key (perfil_id)
       references perfil (id);

alter table usuario_perfil
       add constraint FKotpfkn8c9nmhqqy4pb8hkgp18
       foreign key (usuario_id)
       references usuario (id);