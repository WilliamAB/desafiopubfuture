
# Desafio PubFuture!
Projeto de desafio do programa PubFuture onde os candidatos devem implementar uma solução que auxilie no controle das finanças pessoais. 

**Requisitos**:
 - [x] Utilização de banco de dados relacional
 - [x] Documentação
 - [x] Controle de versionamento (Git e GitHub)
 - [ ] Testes unitários (parcial)
 - [x] Utilização de OOP
 - [x] Utilização de padrões de arquitetura
 - [x] Solução desenvolvida no formato de uma REST API

## Sobre a API

A API foi desenvolvida com Java, Spring Boot e Spring Framework. A URL base da API é `/api`.
A documentação completa com as URLs específicas, parâmetros e métodos (GET, POST, DELETE) pode ser consultada no seguinte endereço [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html) com a aplicação rodando.

## Como rodar
 - Instalar MySQL ou banco de dados de sua preferência;
 - Criar um `schema`no banco, por padrão o nome já configurado na aplicação é `desafio_pubfuture`:
	 - `CREATE SCHEMA desafio_pubfuture;`
 - Configurar o arquivo `application.resources` dentro de `src/main/resources`:
	 - URL de acesso ao banco (conforme banco instalado e nome do schema criado): `spring.datasource.url=jdbc:mysql://localhost:3306/desafio_pubfuture`;
	 - Usuário do banco de dados: `spring.datasource.username=root`
	 - Senha do usuário do banco: `spring.datasource.password=senha`
	 - Dialeto do banco de dados (conforme banco instalado): `spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5InnoDBDialect`
 - Clonar o projeto:
`git clone https://github.com/WilliamAB/desafiopubfuture.git`
 - Executar a aplicação a partir do seguinte comando dentro do diretório clonado:
 `mvnw spring-boot:run`
 - Acesse [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html) para consultar a documentação da API e poder realizar os testes de requisições.
 
## Utilizando o Postman
O Postman é o ideal para testar as requisições da API.

Não é necessário escrever um JSON para fazer cadastros e filtros.

No Postman basta acessar `Body` e dentre as opções escolher `x-www-form-erlencoded`.