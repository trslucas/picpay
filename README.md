# 💸 PicPay Simplificado

Uma aplicação backend em Spring Boot que simula transferências entre usuários com validações, notificações e arquitetura modular.
Parte dessa aplicação foi feita inspirada no desenvolvimento que a [@Fernanda-Kipper](https://github.com/Fernanda-Kipper) fez
Então resolvi aprofundar em alguns temas como: 
- Autenticação via JWT
- Migração do banco para PostgreSQL
- Inserção da arquitetura de notificações através dos serviços da AWS 

---

## 🚀 Como rodar localmente

### 1. Clone o repositório

```bash
git clone https://github.com/seu-usuario/picpay-simplificado.git
cd picpay-simplificado
```

### 2. Configure as variáveis de ambiente

Crie um arquivo `.env` na raiz do projeto com o seguinte conteúdo:

```dotenv
AWS_ACCESS_KEY_ID=""
AWS_SECRET_ACCESS_KEY=""
AWS_REGION=us-""
AWS_SNS_TOPIC_ARN=
AWS_SES_SOURCE_EMAIL=""
```

> ⚠️ **Essas variáveis são obrigatórias para que os serviços AWS funcionem corretamente.**

---

## ⚠️ Observação importante

Este repositório não contém credenciais sensíveis.  
Se você deseja ver a aplicação rodando com as funcionalidades completas (incluindo integração com a AWS), **entre em contato com o desenvolvedor**.

---

## ✅ Requisitos

- Java 17+
- Maven
- Conta AWS com permissões para SNS/SES
- PostgreSQL (ou H2 para testes)

---

## 🔧 Tecnologias

- Spring Boot 3
- PostgreSQL
- AWS SNS / SES
- HikariCP
- Dotenv (para leitura de `.env`)

---

## 📂 Estrutura do projeto

- `config/` – Configurações e beans AWS
- `controller/` – Endpoints REST
- `service/` – Regras de negócio
- `repository/` – Interfaces JPA
- `domain/` – Entidades, enums e models

---


## Diagrama de notificações 


![image](https://github.com/user-attachments/assets/9375988c-c950-4a7e-a26f-fc1596622992)


## 👤 Desenvolvedor

**Lucas Trindade**  
📧 trslucas@outlook.com 
🔗 [LinkedIn](https://www.linkedin.com/in/trslucas)
