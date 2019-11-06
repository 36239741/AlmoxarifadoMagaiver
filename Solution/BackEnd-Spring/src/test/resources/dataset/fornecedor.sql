INSERT INTO tbl_fornecedor(fornecedor_id, bairro, cep, cidade, email, estado, fornecedores_status,longadouro, nome, numero, pais) VALUES (100, 'teste', '13213', 'teste','henrique_nitatori@hotmail.com','SP', true,'teste', 'teste', 123, 'BR');

INSERT INTO tbl_telefone(telefone_id,tipo_telefone,numero,fornecedor_id) VALUES(1,1,'9566365486',100);
