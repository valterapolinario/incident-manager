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

- ** Testes unitarios implementado com uso de banco de dados em memoria, tornando testes integrado com a base, nao se
  fazendo nescessario neste projeto testar o service
- ** Acesse o Swagger da aplicação em http://localhost:8080/swagger-ui/index.html
- ** não será preciso alterar variaveis de ambiente para o funcionamento da aplicação
- ** a Collection do Postman para teste de chamadas a API encontra-se no diretorio **/test/postman

## Adendos:

- ** Implementacao realizada seguindo as diretrizes do descritivo do Desafio, sendo visualizado os seguintes pontos de
  melhoria:
- ** Utilização de Banco de Dados Efetivo como MySql
- ** utilização de bibliotecas de mapper, como MapStruct para reduzir Codigo Boilerplate e garantir compilação segura
  entre outras vantagens
- ** utilização de dockerFile e Docker-compose para facilitar a execução da aplicação e um posterior deploy
- ** otimização de enpoints de consulta, atraves de consulta Paginada, atendendo aos criteiros de busca propostos
- ** imlementacao de endpoints para finalizar e reabrir incidentes, controle de status, para considerar estados entre
  criar e deletar
