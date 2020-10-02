CREATE TABLE biometria (
   id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
   cartao_id BIGINT(20) NOT NULL,
   identificador VARCHAR(100) NOT NULL,
   data_criacao TIMESTAMP NOT NULL,
   FOREIGN KEY (cartao_id) REFERENCES cartao(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


