# Desafio API GFT

Desafio feito pelos starters da GFT.

## funcionalidades

- Sistema de cadastro, login e autenticacao com senha criptografada
- Niveis de acessos definidos
- Upload de imagens
- Retorno em ASC de starters
- Envio de e-mail(nao funcionou tao bem)
- Validar CPF(nao funcionou tao bem)
- CRUD de starters e categorias
- Documentacao em Swagger funcionando

Validar cpf e envio de e-mail serao corrigidos posteriormente

## Techs

Dillinger uses a number of open source projects to work properly:

- [Swagger] - Documentacao da API
- [Spring Boot] - Estrutura em REST
- [MySql] - Armazenamento dos dados
- [Postman e Insomnia] - Testes de endpoints

## Para rodar o projeto:

- Clone-o
- Abra em uma IDE que suporte Spring Boot
- Defina o username e a password do seu Mysql
- Rode o projeto
- e digite o endereco do swagger no seu navegador http://localhost:8080/swagger-ui.html#
- Crie um usuario(recomendo que defina admin como true)
- Faca login e receba o token de autenticacao
- Faca a autenticacao na parte superior direito da seguinte forma: Digite "Bearer" + "{Token gerado}"
- Teste as rotas dada as suas funcionalidades

## Usuario admin padrao
```
Usuário: Admin
Senha: Gft@1234
```
