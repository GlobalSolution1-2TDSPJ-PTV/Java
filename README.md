# 🌊 FloodWatch API

![Ilustração da API FloodWatch](https://placehold.co/800x250/007BFF/FFFFFF?text=FloodWatch+-+API+Inteligente)

## 📖 Sobre o Projeto

O **FloodWatch API** é o backend central da nossa solução para o desafio **FIAP Global Solution 2025: Eventos Extremos**. Este projeto aborda o grave problema das enchentes repentinas em áreas urbanas, oferecendo uma plataforma robusta e escalável para gerenciar a prevenção, o monitoramento e a resposta a emergências.

A API é o cérebro do ecossistema FloodWatch, responsável por:
-   Receber e processar dados em tempo real de sensores IoT (nível de água, chuva).
-   Gerenciar usuários, incluindo cidadãos, agentes da Defesa Civil e ONGs.
-   Registrar e coordenar alertas de risco.
-   Processar pedidos de socorro (SOS) de cidadãos em perigo.
-   Gerenciar a infraestrutura de resposta, como abrigos temporários e drones autônomos.
-   Fornecer endpoints seguros e documentados para o consumo por aplicativos móveis, dashboards de crise e outros sistemas.

## 👥 Integrantes do Grupo

-   **Vinicius Oliveira Coutinho** - RM: 556182
-   **\[Nome Completo do Aluno 2\]** - RM: \[XXXXX\]
-   **\[Nome Completo do Aluno 3\]** - RM: \[XXXXX\]

## 🛠️ Tecnologias Utilizadas

Este projeto foi construído utilizando um stack de tecnologias modernas e robustas, focando em boas práticas de desenvolvimento e segurança.

-   **Linguagem:** Java 17
-   **Framework Principal:** Spring Boot 3
-   **Persistência de Dados:** Spring Data JPA
-   **Banco de Dados:** Oracle (containerizado com Docker)
-   **Segurança:** Spring Security com autenticação baseada em JSON Web Tokens (JWT)
-   **Validação:** Jakarta Bean Validation
-   **Documentação da API:** Springdoc OpenAPI v3 (Swagger UI)
-   **Build e Dependências:** Apache Maven
-   **Containerização:** Docker & Docker Compose
-   **Utilitários:** Lombok

## 📄 Documentação da API (Swagger)

A documentação completa e interativa da API, gerada com o Swagger UI, está disponível para testes e exploração dos endpoints. Após iniciar a aplicação, acesse:

**URL:** [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

Lá você encontrará todos os detalhes sobre os endpoints, os DTOs (Data Transfer Objects) esperados, exemplos de requisições e respostas, além de poder testar a API diretamente pelo navegador.

## 🚀 Como Executar o Projeto

Existem duas maneiras de executar a aplicação: localmente através do Maven ou de forma containerizada com o Docker Compose (recomendado para simular o ambiente completo).

### Pré-requisitos

-   Java JDK 17 ou superior
-   Apache Maven 3.8+
-   Docker e Docker Compose (para a execução com containers)
-   Git

### 1. Execução com Docker Compose (Método Recomendado)

Este método irá iniciar a aplicação Java e um banco de dados Oracle em containers separados e interligados, proporcionando um ambiente completo e isolado.

1.  **Clone o repositório:**
    ```bash
    git clone [https://github.com/GlobalSolution1-2TDSPJ-PTV/Java.git](https://github.com/GlobalSolution1-2TDSPJ-PTV/Java.git)
    cd Java
    ```

2.  **Construa o projeto com Maven:**
    Este passo cria o arquivo `.jar` executável que será usado pelo Docker.
    ```bash
    mvn clean package -DskipTests
    ```

3.  **Inicie os containers com Docker Compose:**
    Este comando irá construir a imagem da sua aplicação e iniciar os serviços definidos no arquivo `docker-compose.yml`.
    ```bash
    docker-compose up --build -d
    ```
    A primeira execução pode levar alguns minutos, pois o Docker precisará baixar a imagem do Oracle.

4.  **Acesse a aplicação:**
    -   API disponível em: `http://localhost:8080`
    -   Swagger UI em: `http://localhost:8080/swagger-ui.html`

5.  **Para parar os containers:**
    ```bash
    docker-compose down
    ```

### 2. Execução Local (Sem Docker)

Este método requer que você tenha uma instância do banco de dados Oracle rodando e acessível pela sua máquina.

1.  **Clone o repositório:**
    ```bash
    git clone [https://github.com/GlobalSolution1-2TDSPJ-PTV/Java.git](https://github.com/GlobalSolution1-2TDSPJ-PTV/Java.git)
    cd Java
    ```

2.  **Configure o `application.properties`:**
    Abra o arquivo `src/main/resources/application.properties` e ajuste as seguintes propriedades para apontar para o seu banco de dados Oracle local:
    ```properties
    spring.datasource.url=jdbc:oracle:thin:@SEU_HOST_ORACLE:1521:SEU_SID
    spring.datasource.username=SEU_USUARIO
    spring.datasource.password=SUA_SENHA
    ```

3.  **Execute a aplicação com Maven:**
    ```bash
    mvn spring-boot:run
    ```

4.  **Acesse a aplicação:**
    -   API disponível em: `http://localhost:8080` (ou a porta que você configurou)
    -   Swagger UI em: `http://localhost:8080/swagger-ui.html`

## 🌐 Visão Geral dos Endpoints da API

Nossa API está estruturada em torno dos seguintes recursos principais:

-   `/api/auth`: Endpoints públicos para autenticação (`/login`) e atualização de tokens (`/refresh`).
-   `/api/usuarios`: Gerenciamento completo de usuários (CRUD). O `POST` é público para permitir o registro de novos usuários.
-   `/api/sensores`: CRUD para os sensores IoT.
-   `/api/leituras-sensor`: Endpoint para receber e consultar leituras dos sensores.
-   `/api/alertas`: Gerenciamento de alertas gerados pelo sistema.
-   `/api/sos`: Gerenciamento de pedidos de socorro enviados pelos cidadãos.
-   `/api/abrigos`: CRUD para os abrigos temporários.
-   `/api/drones`: CRUD para os drones de resposta.

Todos os endpoints, com exceção dos públicos, são protegidos e exigem um token `Bearer JWT` válido no cabeçalho `Authorization`.

---

*Projeto desenvolvido para a Global Solution 2025 da FIAP.*
