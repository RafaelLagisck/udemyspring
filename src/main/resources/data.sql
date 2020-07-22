INSERT INTO Categoria (nome) VALUES ('Informática');
INSERT INTO Categoria (nome) VALUES ('Escritório');

INSERT INTO Produto (nome, preco) VALUES ('Computador', 2000);
INSERT INTO Produto (nome, preco) VALUES ('Mouse', 45);
INSERT INTO Produto (nome, preco) VALUES ('Caneta', 10);

INSERT INTO Produto_Categoria (produto_id, categoria_id) VALUES (1,1);
INSERT INTO Produto_Categoria (produto_id, categoria_id) VALUES (1,2);
INSERT INTO Produto_Categoria (produto_id, categoria_id) VALUES (2,2);
INSERT INTO Produto_Categoria (produto_id, categoria_id) VALUES (3,1);

INSERT INTO Estado (nome) VALUES ('São Paulo');
INSERT INTO Estado (nome) VALUES ('Minaas Gerais');


INSERT INTO Cidade (nome, estado_id) VALUES ('Presidente Prudente', 1);
INSERT INTO Cidade (nome, estado_id) VALUES ('São Paulo', 1);

INSERT INTO Cidade (nome, estado_id) VALUES ('Uberlândia', 2);
INSERT INTO Cidade (nome, estado_id) VALUES ('Belo Horizonte', 2);

INSERT INTO CLIENTE (CPF_OU_CNPJ, EMAIL, NOME, TIPO_CLIENTE) VALUES ('32235978835','lagisck@hotmail.com','Rafael Alves Lagisck',1);

INSERT INTO ENDERECO (BAIRRO, CEP, COMPLEMENTO, LOGRADOURO, NUMERO, CIDADE_ID, CLIENTE_ID) VALUES ('Vila Comercial', '19015300', '', 'Rua Yoyo Bravo','39',1,1);

INSERT INTO ENDERECO (BAIRRO, CEP, COMPLEMENTO, LOGRADOURO, NUMERO, CIDADE_ID, CLIENTE_ID) VALUES ('Jabaquara', '21010200', '', 'Avenida Jabaquara','439',2,1);

INSERT INTO TELEFONE (CLIENTE_ID, TELEFONES) VALUES (1,'1839161549');
INSERT INTO TELEFONE (CLIENTE_ID, TELEFONES) VALUES (1,'18988145053');









