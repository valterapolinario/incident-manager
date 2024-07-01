# Desafio técnico da diazero - Sistema de gestão de incidentes

Desafio proposto para a vaga de desenvolvedor backend na empresa diazero. trata-se de um sistema de gerenciamento de
incidentes.

## Requisitos técnicos do sistema

os requisitos tecnicos encontram-se descritos em **/resources/descricaoDesafio.txt para maiores detalhes

## Como testar a aplicação:

- Faça o clone do repositório na sua máquina, indicado o uso de SSL no github
- com o projeto ja aberto na IDE de sua preferencia execute mvn clean install -DskipTests no terminal
- Execute o comando mvn spring-boot:run no terminal

## Especificações técnicas:

- **Versão do Java:** Java 17
- **Versão do Spring Boot:** 3.2.4
- **Gerenciador de dependências:** Maven
- **Banco de dados:** H2 ( indicado no desafio)

## Considerações:
- ** Testes unitarios implementado com uso de banco de dados em memoria, tornando testes integrado com a base, nao se fazendo nescessario neste projeto testar o service
- ** Acesse o Swagger da aplicação em https://localhost:8080/swagger-ui/index.html
- ** não será preciso alterar variaveis de ambiente para o funcionamento da aplicação
- ** a Collection do Postman para teste de chamadas a API encontra-se no diretorio **/test/postman

## Adendos:
- ** Implementacao realizada seguindo as diretrizes do descritivo do Desafio, sendo visualizado os seguintes pontos de melhoria:
- ** Utilização de Banco de Dados Efetivo como MySql
- ** utilização de bibliotecas de mapper, como MapStruct para reduzir Codigo Boilerplate e garantir compilação segura entre outras vantagens
- ** utilização de dockerFile e Docker-compose para facilitar a execução da aplicação e um posterior deploy
- ** otimização de enpoints de consulta, atraves de consulta Paginada, atendendo aos criteiros de busca propostos
- ** imlementacao de endpoints para finalizar e reabrir incidentes, controle de status, para considerar estados entre criar e deletar
- ** estes pontos de observação, foram implementados na branch feat/apiv2

## Como testar a aplicação(existente na Branch feat/api-v2):

- Faça o clone do repositório na sua máquina, indicado o uso de SSL no github
- tenha certeza de possuir o docker instalado e funcionando na maquina
- Execute o comando docker-compose up --build na pasta raiz ( onde se encontra os arquivos dockerfile e docker-compose.yml
- caso nao possua o docker instalado na maquina , deve se manter o passo de testar a aplicação mencionado na linha 10
- nesta v2 da api foi implementado autorizacao e autenticação com uso de Spring Security, acera dete ponto é importante se atentar :
- ** a aplicação esta configura para inserir na base automaticatiment um usuario default com permissao de ADMIN
- ** na rota **/auth/login deve ser informado Login: emaildeault@gmail.com e senha : admin123
- ** o token devolvido deve ser enviado no cabecalho da requisição dos endpoints **/incidents/**
- a Collection do Postman para teste de chamadas a API em V2 encontra-se no diretorio **/test/postman nomeado V2
- a documentação da API pode ser acessado em https://localhost:8080/swagger-ui/index.html
