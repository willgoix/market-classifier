<h2 align="center">Market Classifier</h2>

<p align="center">	
   <a href="https://www.linkedin.com/in/willian-gois/">
      <img alt="Willian Gois" src="https://img.shields.io/badge/-WillianGois-0f4c75?style=flat&logo=Linkedin&logoColor=white" />
   </a>
</p>

<div align="center">
    <a href="#sobre">Sobre</a>&nbsp;|&nbsp;
    <a href="#sobre-o-código-e-tecnologias">Sobre o código e tecnologias</a>&nbsp;|&nbsp;
    <a href="#tecnologias-utilizadas">Tecnologias</a>&nbsp;|&nbsp;
    <a href="#como-executar-o-projeto">Executando o projeto</a>&nbsp
</div>

## Sobre
**Market Classifier** é uma aplicação Web desenvolvida para possibilitar a avaliação de mercados por funcionários de TI da Grendene. 

Aplicação desenvolvida como teste prático de programação para o ingresso na TI da Grendene.

---

## Sobre o código e tecnologias
A primeira questão durante o planejamento do projeto foi sobre quais tecnologias seriam utilizadas: ou utilizar algo hoje não usual no mercado, como JavaFX, mas que já possuo conhecimentos, ou utilizava Spring, a mais produtiva e utilizada framework para Java de hoje, e também, tecnologia a qual nunca antes havia estudo ou utilizado. No fim, Spring foi a melhor escolha, e além de desenvolver essa aplicação, pude adqurir e aplicar um conhecimento novo. 

Por conta da utilização do Spring, mais da metade do tempo de desenvolvimento se deu frontend (HTML, CSS, JavaScript), além de que, Spring é uma linguagem focada em produtividade, o que curiosamente talvez não é o melhor para este projeto em específico, pois não pude e não precisei escrever tanto código em Java.

Para o armazenamento em memória, foi utilizado o H2 Database, por conta da boa compatibilidade com o sistema Spring Boot.
 
---

## O que pode melhorar
Alguns pontos que devem ser revistos para uma eventual utilização real da aplicação.
-  **Banco de Dados**: Implementar um banco de dados persistente.
-  **Segurança**: Desenvolver uma segurança para as rotas HTTP.
-  **Segurança de contas**: Exigir confirmação (via email, por exemplo) ao registrar uma conta, tanto para segurança como para evitar criação de conta somente para avaliar.
-  **Interface**: Como não era o foco do teste, o frontend pode melhorar bastante.
-  **Finalização de ideias**: Algumas ideias do início do projeto, como o registro e exibição dos avaliadores de um mercado, não foram concretizadas. Alguns resquíscios ainda continuam no código, como a escolha da equipe de TI durante o registro.
-  **Testes**: Implementar testes automatizados.

---

## Tecnologias utilizadas
-  [Java](https://www.java.com/)
-  [Spring Boot](https://spring.io/projects/spring-boot)
-  [Spring Data](https://spring.io/projects/spring-data)
-  [Spring Security](https://spring.io/projects/spring-security)
-  [Thymeleaf](https://www.thymeleaf.org)
-  [HTML](https://www.w3schools.com/css/)
-  [CSS](https://www.w3schools.com/html/)

---

## Como executar o projeto
Exigências mínimas para executar o projeto:
-  Maven 3.2+ instalado;
-  JDK 6+ instalado;
-  E caso utilizando Windows, um Shell instalado (ex: Git Bash). 
``` bash
# Clonar o repositório
git clone https://github.com/willgoix/market-classifier

# Entrar na raíz do projeto
cd market-classifier

# Executar a aplicação
./mvnw spring-boot:run

Agora, acesse a aplicação em http://localhost:8080/.
```

<div align="center">
  <sub>Desenvolvido com ❤ por <a href="https://github.com/willgoix">Willian Gois</a>.</sub>
</div>