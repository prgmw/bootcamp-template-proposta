CREATE TABLE cartao (
   id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
   proposta_id BIGINT(20) NOT NULL,
   cartao VARCHAR(100) NOT NULL,
   FOREIGN KEY (proposta_id) REFERENCES proposta(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



