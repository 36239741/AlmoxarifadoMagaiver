INSERT INTO fornecedor(id, bairro, cep, cidade, email, estado, fornecedores_status,longadouro, nome, numero, pais) VALUES (100, 'teste', '13213', 'teste','henrique_nitatori@hotmail.com','SP', true,'teste', 'teste', 123, 'BR');
INSERT INTO item(id,descricao,codigo,item_status,local_armazenamento,quantidade,valor,fornecedor_id) VALUES(100,'Martelo','1234567',true,'Loja1', 40,40, 100);
INSERT INTO telefone(id,tipo_telefone,numero,fk_fornecedor) VALUES(1,1,'9566365486',100);
INSERT INTO fornecedor(id, bairro, cep, cidade, email, estado, fornecedores_status,longadouro, nome, numero, pais) VALUES (200, 'teste2', '13213', 'teste2','henrique_nitatori@hotmail.com','SP', true,'teste2', 'teste2', 123, 'BR');

