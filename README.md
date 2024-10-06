![perfilreadme](https://github.com/user-attachments/assets/ac859c9e-1938-4183-b639-1a7cee06dff0)


# Sistema de Reserva de Hotéis - Java, Swing, MySQL

Este é o meu primeiro projeto completo desenvolvido em **Java** utilizando **Swing** para a interface gráfica e **MySQL** como banco de dados. Este sistema foi criado como parte de um trabalho de **Programação Orientada a Objetos (POO)** durante o curso de **Análise e Desenvolvimento de Sistemas (ADS)**.

## Funcionalidades

- **Gerenciamento de Hotéis**: 
  - Cadastro de novos hotéis com nome, descrição e preço.
  - Listagem dos hotéis disponíveis com informações detalhadas.
  
- **Gerenciamento de Quartos**: 
  - Cadastro e listagem de quartos com número, status (livre ou ocupado) e preço.
  
- **Sistema de Usuários**: 
  - Registro e login de usuários, com autenticação e validação de dados.
  
- **Reservas**: 
  - Realização de reservas de hotéis, selecionando destino, check-in, check-out e quantidade de hóspedes.

## Tecnologias Utilizadas

- **Java**: Linguagem principal do projeto.
- **Swing**: Interface gráfica para interação do usuário.
- **MySQL**: Banco de dados para armazenar as informações de usuários, hotéis, quartos e reservas.
- **JDBC**: Utilizado para conectar o Java ao MySQL.

## Como Configurar o Projeto

### Pré-requisitos

1. **Java Development Kit (JDK)**: Certifique-se de que o **JDK** está instalado. Este projeto foi desenvolvido usando o **JDK 21**.
2. **MySQL**: O MySQL deve estar instalado e configurado em sua máquina.
3. **MySQL Connector/J**: O conector JDBC para MySQL deve ser adicionado ao seu projeto.

### Configuração do Banco de Dados

1. Crie o banco de dados para o projeto com o nome `ReservaHotel`:
   
   ```sql
   CREATE DATABASE ReservaHotel;
