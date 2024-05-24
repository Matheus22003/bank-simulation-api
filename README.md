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

#### Clientes
- `POST /api/v1/clientes`: Cria um novo cliente.
- `PUT /api/v1/clientes`: Atualiza um cliente específico por ID.
- `GET /api/v1/clientes`: Retorna todos os clientes.
- `GET /api/v1/clientes/{id}`: Retorna um cliente específico por ID.
- `DELETE /api/v1/clientes/{id}`: Deleta um cliente específico por ID.

#### Transações
- `POST /api/v1/transacoes`: Cria uma nova transação.
- `DELETE /api/v1/transacoes/{id}`: Deleta uma transação específica por ID.

#### User
- `POST /api/v1/save`: Cria um novo User.
- `GET /api/v1/users`: Retorna todos os users.
- `POST /api/v1/login`: Cria tokens para autenticação JWT.
- `POST /api/v1/refreshToken`: Faz um refresh dos tokens já criados.
## Licença

Este projeto é de propriedade de Matheus de Oliveira Simoes. Todos os direitos reservados.
