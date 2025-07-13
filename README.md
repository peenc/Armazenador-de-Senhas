# 🔐 Armazenador de Senhas

Projeto pessoal com o objetivo de adquirir experiência prática com **Spring Boot**, **MySQL** e desenvolvimento **Front-End** utilizando **HTML** e **CSS**.

## 📘 Descrição

Este é um sistema local para armazenamento de senhas, onde o usuário pode registrar e gerenciar credenciais de diferentes serviços, como:

- Facebook
- Instagram
- Twitter
- Outros

As informações (nome do serviço, login e senha) são salvas no banco de dados e podem ser:

- Atualizadas
- Excluídas

Cada membro da família pode ter suas próprias credenciais cadastradas.

## 🛡️ Segurança e Criptografia

- As **senhas são armazenadas de forma criptografada** no banco de dados.
- Foi implementada uma camada de **segurança para proteger os dados sensíveis**, evitando exposição de informações em texto puro.
- A criptografia garante que mesmo em caso de acesso indevido ao banco de dados, os dados armazenados estejam protegidos.

> ✅ Esse cuidado com segurança torna o projeto aplicável como base para sistemas reais que lidam com dados sensíveis.

## 🎯 Objetivo

Resolver uma necessidade familiar de forma prática e didática, com foco em:

- Aprendizado de **Back-End com Spring Boot**
- Integração com **banco de dados MySQL**
- Estruturação de um **Front-End básico** com HTML + CSS
- Implementação de **segurança de dados**

## 🛠️ Tecnologias Utilizadas

| Tecnologia | Descrição |
|------------|-----------|
| 🟠 Java | Lógica e estruturação da aplicação com Spring Boot |
| 🟡 Spring Boot | Framework para desenvolvimento Back-End |
| 🟦 MySQL | Banco de dados relacional |
| 🌐 HTML | Estruturação das páginas |
| 🎨 CSS | Estilização básica das páginas |
| 🔐 Criptografia | Proteção dos dados sensíveis (senhas) |

## 🚀 Como Executar

1. Clone o repositório:
2. Importe o projeto em sua IDE Java (ex: IntelliJ ou Eclipse).
3. Certifique-se de que o MySQL esteja rodando e atualizado com as credenciais no application.properties.
4. Execute a aplicação via Spring Boot.

Acesse no navegador: http://localhost:8080

📌 Notas
Este projeto foi motivado por uma necessidade real e familiar, e é também uma forma de consolidar conhecimentos adquiridos ao longo dos estudos.


