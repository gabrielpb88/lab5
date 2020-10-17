drop database if exists lab5;
create database if not exists lab5;
drop user 'lab5'@'localhost';

use lab5;

create user 'lab5'@'localhost' identified by 'lab5';

grant select, insert, update, delete on lab5.* to 'lab5'@'localhost';

CREATE TABLE cli_cliente(
	cli_id BIGINT NOT NULL AUTO_INCREMENT,
    dtype VARCHAR(255),
    nome VARCHAR(255),
    cpf VARCHAR(14),
    cnpj VARCHAR(18),
    endereco VARCHAR(255),
    CONSTRAINT pk_cli_cliente PRIMARY KEY(cli_id)
);

CREATE TABLE for_fornecedor(
	for_id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(255),
    cnpj VARCHAR(19),
    CONSTRAINT pk_for_fornecedor PRIMARY KEY(for_id)
);

CREATE TABLE ite_item(
	ite_id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(255),
    preco double,
    for_id BIGINT,
    CONSTRAINT pk_ite_item PRIMARY KEY (ite_id),
    CONSTRAINT fk_for_fornecedor FOREIGN KEY (for_id)
    REFERENCES for_fornecedor(for_id)
);

CREATE TABLE ped_pedido(
	ped_id BIGINT NOT NULL AUTO_INCREMENT,
    cli_id BIGINT,
    data_do_pedido DATE,
    CONSTRAINT pk_ped_pedido PRIMARY KEY(ped_id),
    CONSTRAINT fk_cli_cliente FOREIGN KEY(cli_id)
    REFERENCES cli_cliente(cli_id)
);

CREATE TABLE item_pedido(
	id BIGINT NOT NULL AUTO_INCREMENT,
	ped_id BIGINT NOT NULL,
    ite_id BIGINT NOT NULL,
    preco DOUBLE,
    quantidade BIGINT,
    CONSTRAINT pk_item_pedido PRIMARY KEY(id),
    CONSTRAINT fk_ped_pedido FOREIGN KEY (ped_id)
    REFERENCES ped_pedido(ped_id),
    CONSTRAINT fk_ite_item FOREIGN KEY (ite_id)
    REFERENCES ite_item(ite_id)
);

CREATE TABLE pag_pagamento(
	pag_id BIGINT NOT NULL AUTO_INCREMENT,
    ped_id BIGINT,
    valor DOUBLE,
    CONSTRAINT pk_pag_pagamento PRIMARY KEY(pag_id),
    CONSTRAINT fk_pag_ped_pedido FOREIGN KEY (ped_id)
    REFERENCES ped_pedido(ped_id)
);

CREATE TABLE pag_pagamento_cartao(
	pag_id BIGINT NOT NULL AUTO_INCREMENT,
    parcelas INTEGER,
    CONSTRAINT pk_pag_pagamento_cartao PRIMARY KEY(pag_id)
);

CREATE TABLE pag_pagamento_dinheiro(
	pag_id BIGINT NOT NULL AUTO_INCREMENT,
    desconto DOUBLE,
    CONSTRAINT pk_pag_pagamento_dinheiro PRIMARY KEY(pag_id)
);

CREATE TABLE usr_usuario(
    usr_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL
);

CREATE TABLE roles(
    rol_id BIGINT NOT NULL,
    role_name VARCHAR(255) NOT NULL,
    CONSTRAINT pk_roles PRIMARY KEY(rol_id)
);

CREATE TABLE usr_has_roles(
    role_id BIGINT NOT NULL,
    usuario_id BIGINT NOT NULL,
    CONSTRAINT fk_usuario FOREIGN KEY (usuario_id) REFERENCES
    usr_usuario(usr_id),
    CONSTRAINT fk_role FOREIGN KEY (role_id) REFERENCES
    roles(rol_id),
    CONSTRAINT pk_roles PRIMARY KEY(role_id, usuario_id)
);

INSERT INTO usr_usuario (nome, senha) VALUES ('admin', '$2y$12$.RWfoFef/X0E3ebgeTSW5ObdF2RSqGRxGU73bRBFBKVJboSp4JYJa');
INSERT INTO roles (role_name) values ('ROLE_ADMIN');
INSERT INTO usr_has_roles VALUES (1, 1);