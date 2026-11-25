CREATE DATABASE IF NOT EXISTS loja;

USE loja;

CREATE TABLE IF NOT EXISTS categorias (
                                          id INT AUTO_INCREMENT PRIMARY KEY,
                                          nome VARCHAR(100) NOT NULL,
    descricao VARCHAR(255)
    );

CREATE TABLE IF NOT EXISTS produtos (
                                        id INT AUTO_INCREMENT PRIMARY KEY,
                                        nome VARCHAR(100) NOT NULL,
    preco DECIMAL(10,2) NOT NULL,
    estoque INT NOT NULL,
    categoria_id INT NOT NULL,

    FOREIGN KEY (categoria_id)
    REFERENCES categorias(id)
    );