# Tech-challenge-fase5
## Repositório para o Tech Challenge Fase 5 - Grupo 30
Este repositório contém o código-fonte e a documentação para o Tech Challenge - Fase 5, desenvolvido pelo Grupo 30.

O projeto consiste em uma sistema de e-commerce utilizando uma arquitetura com microserviços. Além do projetos Spring Framework, Spring Boot e Sping Data, também utilizamos neste projeto o Spring Security.

## 1- Relatório Técnico
Tecnologias e ferramentas utilizadas

* Linguagem de programação: 

    * Java 17

* Framework:
    * Spring Boot 3.2.2

* Bibliotecas:
  * Spring Web
  * Spring Security
  * OpenAPI
  * Lombok
    
* Banco de dados:
  * PostgreSQL
    
* Outras ferramentas:
  * Docker 

## Configurações da solução

### Banco de Dados

Utilizamos um banco de dados para cada microserviço e configuramos dois profiles, um profile para trabalharmos com H2 e outro para trabalharmos com o PostgreSQL:

Configuração de teste utilizando o H2:

https://github.com/rcsim/tech-challenge-fase5/blob/main/ms-items/src/main/resources/application-test.properties

https://github.com/rcsim/tech-challenge-fase5/blob/main/ms-shopping-cart/src/main/resources/application-test.properties

https://github.com/rcsim/tech-challenge-fase5/blob/main/ms-user-manager/src/main/resources/application-test.properties

https://github.com/rcsim/tech-challenge-fase5/blob/main/payment/src/main/resources/application-test.properties


Configuração de produção utilizando o PostgreSQL:

https://github.com/rcsim/tech-challenge-fase5/blob/main/ms-items/src/main/resources/application-dev.properties

https://github.com/rcsim/tech-challenge-fase5/blob/main/ms-shopping-cart/src/main/resources/application-dev.properties

https://github.com/rcsim/tech-challenge-fase5/blob/main/ms-user-manager/src/main/resources/application-dev.properties

https://github.com/rcsim/tech-challenge-fase5/blob/main/payment/src/main/resources/application-dev.properties


### Container


Criamos um container para cada aplicação e outro para cada banco de dados e uma rede no modo bridge para ter acesso ao containers via localhost:

https://github.com/rcsim/tech-challenge-fase5/blob/2a89677a7e2c639881f65117dfad10388205568c/docker-compose.yml#L1-L137

Também adicionamos o arquivo Dockerfile que gerencia o processo de build da aplicação através do Maven e JDK, já inicializando a aplicação:

https://github.com/rcsim/tech-challenge-fase5/blob/2a89677a7e2c639881f65117dfad10388205568c/payment/Dockerfile#L1-L15

Para criação dos container, compilar e rodar a applicação é necessário apenas o comando:


docker-compose up -d

![image](https://github.com/rcsim/tech-challenge-fase5/assets/30301531/ee742f8b-1467-4e02-b7f3-35c6e25e9a92)



### Documentação das APIS 
Adicionamos a geração automática da documentação através da biblioteca SpringDoc OpenAPI, a documentação pode ser acessada enquanto a aplicação estiver rodando em http://localhost:8080/swagger-ui/index.html#/:

Microserviço Users
http://localhost:8081/swagger-ui/index.html#/
![image](https://github.com/rcsim/tech-challenge-fase5/assets/30301531/05912cfc-cf26-4eed-bfdf-925a84ad9a99)


Microserviço Items
http://localhost:8082/swagger-ui/index.html#/
![image](https://github.com/rcsim/tech-challenge-fase5/assets/30301531/dc24a48e-3074-40c5-9ae7-c0321e8d44df)


Microserviço Shopping-Carts
http://localhost:8083/swagger-ui/index.html#/
![image](https://github.com/rcsim/tech-challenge-fase5/assets/30301531/0f88711d-3941-4513-af58-ad06f2956a5c)


Microserviço Payment
http://localhost:8084/swagger-ui/index.html#/
![image](https://github.com/rcsim/tech-challenge-fase5/assets/30301531/e71fb10f-6f48-42fb-a08a-05e556aa7244)



#### Arquivo POSTMAN

Disponibilizamos um de arquivo JSON com todas as requisições Postman para testar a API, os arquivos estão disponíveis nos seguintes links:

https://github.com/rcsim/tech-challenge-fase5/blob/main/Tech-Challenge-5.postman_collection.json


## Conclusões 

O projeto foi concluído com êxito, atendendo integralmente aos requisitos propostos no desafio. A escolha criteriosa das tecnologias e ferramentas desempenhou um papel fundamental, proporcionando um ambiente propício para o desenvolvimento eficiente e robusto do sistema. Destaca-se o enfoque dedicado à implementação de endpoints reativos com Spring WebFlux, constituindo o principal ponto de aprendizado nesta etapa. Vale ressaltar que, devido à novidade desse paradigma para alguns membros do grupo, foram necessários esforços adicionais para assimilação e aplicação efetiva.

As principais dificuldades encontradas estão relacionadas à incorporação do protocolo HLS para o streaming de vídeos e à hospedagem destes na AWS S3. Conforme mencionado anteriormente, o paradigma de programação reativa também se configurou como um ponto de atenção, dada sua novidade para parte dos integrantes. Essas questões exigiram uma abordagem cuidadosa e estratégica para superação, demonstrando a capacidade do grupo em lidar com desafios complexos.



