# mback-user

Este é o projeto `mback-user`, um projeto Java Spring Boot gerenciado pelo Gradle.

## Requisitos

- Java 17
- Gradle 8.6

## Configuração

Clone o repositório usando o seguinte comando:

```bash
git clone git@github.com:marsleite/mback-user.git
```

## Construção

Para construir o projeto, navegue até o diretório do projeto e execute o seguinte comando:

```bash
./gradlew build
```

Testes

```bash
./gradlew clean build
```
## Endpoints
Este projeto fornece os seguintes endpoints:  
- GET /api/users: Retorna uma lista de todos os usuários.
- POST /api/users: Cria um novo usuário.
- GET /api/users/{id}: Retorna detalhes de um usuário específico.
- PUT /api/users/{id}: Atualiza um usuário existente.
- DELETE /api/users/{id}: Exclui um usuário.

## Spring Security
Este projeto utiliza o Spring Security para autenticação e autorização. As senhas dos usuários são criptografadas usando BCrypt. A autenticação é feita através de um token JWT que é gerado após o login bem-sucedido.

## Gerenciamento de Usuários
Os usuários podem ser gerenciados através dos endpoints da API. Eles podem ser criados, atualizados, visualizados e excluídos. Cada usuário tem um nome, email e senha. A senha é criptografada antes de ser armazenada no banco de dados.