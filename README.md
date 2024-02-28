# Barbearia
Api para agendamento de horários

## Status do Projeto

![Static Badge](https://img.shields.io/badge/status-em_desenvolvimento-brightgreen?style=for-the-badge&logo=appveyor)

## Documentação da API

```yaml
TBD
```

## Variáveis de Ambiente

Para rodar esse projeto, você vai precisar adicionar as seguintes variáveis de ambiente.

- `DB_URL`: URL do banco de dados
- `DB_USER`: Usuário do banco de dados
- `DB_PASS`: Senha do banco de dados
- `MAIL_HOST`: Host do provedor de e-mail
- `MAIL_PORT`: Porta do provedor de e-mail
- `MAIL_USER`: Usuário do provedor de e-mail
- `MAIL_PASS`: Senha do provedor de e-mail
- `TOKEN_SECRET`: Secret para encriptar o token jwt

## Rodando localmente

Clone o projeto

```bash
  git clone git@github.com:gasfgrv/barbearia.git
```

Entre no diretório do projeto

```bash
  cd barbearia
```

Instale as dependências

```bash
  mvn clean package -DskipTests
```

Subir recursos na AWS

```bash
  cd infra/
  terraform init
  terraform plan
  terraform apply -auto-approve
```

Inicie a aplicação, pode ser pela IDE (Intellij ou Eclipse), ou rodando o seguinte comando:

```bash
    java \
    -jar target/dynamodb-test-0.0.1-SNAPSHOT.jar
```

Use a collection para ajudar com os endpoints:

[![Run in Insomnia}](https://insomnia.rest/images/run.svg)](https://insomnia.rest/run/?label=barbearia&uri=https%3A%2F%2Fraw.githubusercontent.com%2Fgasfgrv%2Fbarbearia%2Fmaster%2Fdocs%2Fcollection.yaml)

## Rodando os testes

Para rodar os testes, rode o seguinte comando

```bash
  mvn tests
```

## Stack utilizada

- Java 17
- Spring Boot 3
- Maven

### Dependências:

- spring-boot-starter-actuator:3.2.1
- spring-boot-starter-data-jpa:3.2.1
- spring-boot-starter-hateoas:3.2.1
- spring-boot-starter-mail:3.2.1
- spring-boot-starter-security:3.2.1
- spring-boot-starter-validation:3.2.1
- spring-boot-starter-web:3.2.1
- flyway-core:9.22.3
- spring-cloud-starter-circuitbreaker-resilience4j:3.1.0
- modelmapper:3.2.0
- springdoc-openapi-starter-webmvc-ui:2.3.0
- org.springframework.boot:spring-boot-devtools:3.2.1
- micrometer-registry-prometheus:1.12.1
- postgresql:42.6.0
- lombok:1.18.30
- spring-boot-starter-test:3.2.1
- spring-boot-testcontainers:3.2.1
- spring-security-test:6.2.1
- testcontainers:junit-jupiter:1.19.3
- testcontainers:postgresql:1.19.3
- testcontainers:localstack:1.19.3
- java-jwt:4.4.0
- greenmail-junit5:1.6.11
- rest-assured:5.3.1

## Autores

- [@gasfgrv](https://www.github.com/gasfgrv)