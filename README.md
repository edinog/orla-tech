
# 📦 Projetos API

API RESTful desenvolvida em Java 11+ com Spring Boot, PostgreSQL e JPA/Hibernate para gerenciamento de Projetos e Funcionários, com relacionamento N:N.

---

## ⚙️ Tecnologias Utilizadas

- Java 11
- Spring Boot
- Spring Data JPA (Hibernate)
- PostgreSQL
- ModelMapper
- Bean Validation
- Swagger 3.0
- JUnit 5 + MockMvc
- Maven

---

## 🧠 Padrões de Projeto

- DTO (Data Transfer Object)
- Service Layer Pattern
- Repository Pattern (Spring Data)
- Adapter Pattern (ModelMapper)
- ControllerAdvice Pattern

---

## 📌 Funcionalidades

- Criar um novo funcionario (`POST /api/funcionario`)
- Criar um novo projeto (`POST /api/projetos`)
- Listar todos os projetos com funcionários (`GET /api/projetos`)
- Validações de entrada com Bean Validation
- Tratamento global de exceções com `@ControllerAdvice`
- Conversão automática de DTOs com ModelMapper
- Testes unitários e integrados

---

## 🚀 Como Executar

### Pré-requisitos

- Java 11+
- PostgreSQL (porta padrão 5432)
- Maven

### Configuração do banco

Crie o banco de dados no PostgreSQL:

```sql
CREATE DATABASE desenvolvimento;
```

### Configuração da aplicação

Edite o arquivo `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/desenvolvimento
spring.datasource.username=postgres
spring.datasource.password=postgres
```

### Executar

```bash
mvn clean spring-boot:run
```

---

## 🔎 Documentação Swagger

Acesse:

```
http://localhost:8080/swagger-ui/index.html
```

---

## ✅ Testes

Executar testes unitários e integrados:

```bash
mvn test
```

---

## 📁 Estrutura do Projeto

```
src
├── main
│   ├── java
│   │   └── com.orlatech.projetosapi
│   │       ├── controller
│   │       ├── dto
│   │       ├── entity
│   │       ├── repository
│   │       ├── service
│   │       └── config
│   └── resources
│       └── application.properties
└── test
    ├── unit
    └── integration
```

---

## 👨‍💻 Autor

Desenvolvido por Edivaldo Nogueira
https://www.linkedin.com/in/edivaldo-nogueira-1a240263/

---
