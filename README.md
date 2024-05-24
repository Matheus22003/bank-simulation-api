# Bank Simulation API

API para o funcionamento do front-end de simulação de transações bancárias.

## Requisitos do Sistema

- Java 17
- Spring Boot 3.2.5
- Maven 3.9.5

## Instalação

1. Clone o repositório para sua máquina local.
    ```bash
    git clone https://github.com/seu-usuario/bank-simulation-api.git
    ```

2. Navegue até o diretório do projeto.
    ```bash
    cd bank-simulation-api
    ```

3. Compile o projeto e baixe as dependências.
    ```bash
    mvn clean install
    ```

4. Inicie a aplicação.
    ```bash
    mvn spring-boot:run
    ```

## Uso

A API estará disponível em `http://localhost:8080`. Você pode utilizar ferramentas como Postman ou cURL para interagir com a API.

### Endpoints Principais

- `GET /api/transactions`: Retorna todas as transações.
- `POST /api/transactions`: Cria uma nova transação.
- `GET /api/transactions/{id}`: Retorna uma transação específica por ID.
- `PUT /api/transactions/{id}`: Atualiza uma transação específica por ID.
- `DELETE /api/transactions/{id}`: Deleta uma transação específica por ID.

## Licença

Este projeto é de propriedade de Matheus de Oliveira Simoes. Todos os direitos reservados.
