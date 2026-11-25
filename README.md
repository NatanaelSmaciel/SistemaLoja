# Sistema de Gerenciamento de Loja

## Sobre o projeto

Sistema desenvolvido em Java para a disciplina de Programação Orientada a Objetos (POO), com o objetivo de gerenciar produtos e categorias de uma loja.

O sistema utiliza um banco de dados MySQL e possui integração com uma API externa.

## Tecnologias utilizadas

- Java 17
- Maven
- MySQL
- JDBC
- Dotenv
- Gson
- Fake Store API
- IntelliJ IDEA
- Git e GitHub

## Funcionalidades

- Cadastro, listagem, atualização e exclusão de categorias.
- Cadastro, listagem, atualização e exclusão de produtos.
- Relacionamento entre produtos e categorias.
- Validação de dados.
- Tratamento de exceções.
- Consulta de produtos através de uma API externa.
- Menu interativo pelo terminal.

## Estrutura do projeto

- **model:** classes que representam as entidades.
- **dao:** operações realizadas no banco de dados.
- **database:** configuração da conexão com o MySQL.
- **service:** comunicação com a API externa.
- **Main:** execução do sistema e menu interativo.

## Banco de dados

O sistema utiliza duas tabelas:

- categorias
- produtos

O relacionamento entre as tabelas é de um para muitos (1:N).

## Como executar

1. Instale o Java 17, Maven e MySQL.
2. Clone o repositório.
3. Execute o arquivo `database.sql` no MySQL.
4. Crie uma cópia do arquivo `.env.example` com o nome `.env`.
5. Configure o usuário e a senha do MySQL no arquivo `.env`.
6. Carregue as dependências do Maven.
7. Execute a classe `Main.java`.

## API externa

O projeto utiliza a Fake Store API para consultar produtos externos.

Endpoint: https://fakestoreapi.com/products

As informações recebidas em JSON são interpretadas utilizando a biblioteca Gson.

## Segurança

As credenciais do banco de dados são armazenadas no arquivo `.env`, que não deve ser publicado no GitHub.

## Autor

- Natanael Siqueira Maciel
- Matrícula final: 0025

Projeto acadêmico desenvolvido para a disciplina de Programação Orientada a Objetos.