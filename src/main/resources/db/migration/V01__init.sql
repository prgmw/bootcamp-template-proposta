CREATE TABLE proposta (
   id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
   documento VARCHAR(14) NOT NULL,
   nome VARCHAR(100) NOT NULL,
   email VARCHAR(100) NOT NULL,
   endereco VARCHAR(250) NOT NULL,
   salario DECIMAL(15,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


