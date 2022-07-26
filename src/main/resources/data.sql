INSERT INTO categoria(nome, status) VALUES ('INFORMÁTICA', 'ATIVA');
INSERT INTO categoria(nome, status) VALUES ('LIVROS', 'ATIVA');
INSERT INTO categoria(nome, status) VALUES ('FILMES', 'ATIVA');
INSERT INTO categoria(nome, status) VALUES ('MODA', 'INATIVA');

INSERT INTO produto(nome, descricao, preco_unitario, quantidade_estoque, categoria_id ) VALUES ("impressora", "impressora", 300, 10, 1);

INSERT INTO cliente(cpf, bairro, cidade, complemento, estado, numero, rua, nome, telefone)
VALUES (12345678900, "Glória", "Rio de Janeiro", "611, "RJ", 39, "Rua Taylor", "Julia", "2199999999");

INSERT INTO cliente(cpf, bairro, cidade, complemento, estado, numero, rua, nome, telefone)
VALUES (12345678901, "Glória", "Rio de Janeiro", "611, "RJ", 39, "Rua Taylor", "Sara", "2199999998");


INSERT INTO pedido(data, desconto, tipo_desconto, cliente_id) VALUES ("2022/02/01", "0.00", "NENHUM", 1);

INSERT INTO item_pedido(desconto, preco_unitario, quantidade, tipo_desconto, pedido_id, produto_id)
VALUES (0.00, 300, 1, "NENHUM", 1, 1);


SELECT c.nome, COUNT(item.id) AS quantidade, SUM((item.preco_unitario * item.quantidade)) AS montanteVendido
FROM pedido
JOIN item_pedido item
JOIN produto p
JOIN categoria c
WHERE pedido.id = item.pedido_id AND item.produto_id = p.id AND p.categoria_id = c.id
GROUP BY c.id, pedido.id, item.id;






