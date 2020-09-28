CREATE TABLE biometria (
   id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
   proposta_id BIGINT(20) NOT NULL,
   identificador VARCHAR(100) NOT NULL,
   data_criacao TIMESTAMP NOT NULL,
   FOREIGN KEY (proposta_id) REFERENCES proposta(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


