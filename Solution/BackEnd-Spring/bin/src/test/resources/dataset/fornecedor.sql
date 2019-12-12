INSERT INTO fornecedor(id, bairro, cep, cidade, email, estado, fornecedores_status,longadouro, nome, numero, pais) VALUES (100, 'teste', '13213', 'teste','henrique_nitatori@hotmail.com','SP', true,'teste', 'teste', 123, 'BR');

INSERT INTO telefone(id,tipo_telefone,numero,fk_fornecedor) VALUES(1,1,'9566365486',100);
