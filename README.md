# LAB5

Repositório para a disciplina ```Laboratório de Desenvolvimento em Banco de Dados V```
Professor:  Emanuel Mineda Carneiro

Descrição dos Requisitos disponivéis no arquivo: ```01_Apresentacao_Disciplina.pdf```

### Datas de Entrega: 
11/09/2020 - Spring Boot + Spring Data JPA

02/10/2020 - REST

23/10/2020 - Segurança

13/11/2020 - Vue.js

### Integrantes
-Andre

-Bruno

-Gabriel

-Leticia

-Vanessa


### Executando

1. MySQL deve estar rodando no localhost na porta padrão (3306), ou altere a variavel de configuracao `spring.datasource.url` do arquivo [application.properties](./src/main/resources/application.properties) para apontar para a sua instancia do MySQL. 

2. No arquivo [application.properties](./src/main/resources/application.properties) altere os campos `spring.datasource.username` e `spring.datasource.password` para um usuario que já exista no banco, ou crie um novo usuario no MySQL com login e senha `lab5`

3. Antes de rodar a aplicação pela primeira vez, crie o banco e as tabelas usando o script [script_banco.sql](script_banco.sql).
Depois que o banco estiver criado não há necessidade de reexecutar este script nas próximas execuções.

4. Para a apresentação, basta executar o arquivo [PedidoRepositoryTest](./src/test/java/br/gov/sp/fatec/lab5/PedidoRepositoryTest.java).