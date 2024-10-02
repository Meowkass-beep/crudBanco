CREATE TABLE tb_usuario(
id int AUTO_INCREMENT,
nome varchar(255) NOT NULL,
email varchar(255) NOT NULL,
cpf numeric(11) NOT NULL,
senha varchar(255) NOT NULL,
id_Endereco int NOT NULL,

CONSTRAINT pk_tbusUario PRIMARY KEY(id),
CONSTRAINT un_CPFtbUsuario UNIQUE(cpf),
CONSTRAINT un_EMAILtbUsuario UNIQUE(email),
CONSTRAINT fk_tbUsuarioEndereco FOREIGN KEY(id_Endereco) REFERENCES tb_endereco(id)
) ENGINE=InnoDB;

CREATE TABLE tb_endereco(
id int AUTO_INCREMENT,
logradouro varchar(255) NOT NULL,
cep varchar(255) NOT NULL,
numero varchar(255) NOT NULL,

CONSTRAINT pk_tbEndereco PRIMARY KEY(id)
) ENGINE=InnoDB;

select * from tb_endereco