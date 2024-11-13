
# Projeto de Gerenciamento de Perguntas e Respostas 📝

Este projeto é uma API RESTful em Java para gerenciamento de perguntas e respostas pessoais. Cada usuário pode criar e responder suas próprias perguntas, funcionando como um diário de conhecimento pessoal.

## Tecnologias Utilizadas 🛠️

-   **Java 11+**
-   **Spring Boot**: Framework para criar a aplicação REST.
-   **Maven**: Gerenciamento de dependências.
-   **PostgreSQL**: Banco de dados para persistência dos dados.
-   **JWT (JSON Web Token)**: Autenticação e autorização dos usuários.


## Funcionalidades 🌟
#### **Autenticação**

-   **Login**: Permite que o usuário autentique-se e receba um token JWT para acesso às demais funcionalidades da aplicação.

#### **Gerenciamento de Usuários**

-   **Cadastrar Usuário**: Registra um novo usuário no sistema com um nome de usuário, senha e outras informações necessárias.
-   **Buscar Usuário por ID**: Recupera as informações de perfil de um usuário específico.
-   **Atualizar Usuário**: Atualiza todas as informações de um usuário, como nome, email e senha.
-   **Atualizar Parcialmente (Patch)**: Permite atualizar campos específicos de um usuário, como somente o nome ou a senha, sem modificar as demais informações.
-   **Excluir Usuário**: Remove um usuário do sistema.

#### **Gerenciamento de Perguntas**

-   **Criar Pergunta**: Permite que um usuário crie uma nova pergunta, associada a um título e conteúdo.
-   **Buscar Pergunta por ID**: Recupera uma pergunta específica usando seu ID.
-   **Atualizar Pergunta**: Permite que um usuário edite os detalhes de uma pergunta.
-   **Excluir Pergunta**: Remove uma pergunta do sistema. Se a pergunta tiver respostas associadas, elas são mantidas ou removidas conforme a lógica de exclusão de respostas.

#### **Gerenciamento de Respostas**

-   **Buscar Respostas por Pergunta**: Recupera todas as respostas associadas a uma pergunta específica.
-   **Criar Resposta**: Permite que um usuário crie uma resposta para uma pergunta. A resposta é associada à pergunta e ao usuário.
-   **Atualizar Resposta**: Atualiza o texto de uma resposta existente.
-   **Excluir Resposta**: Remove uma resposta do sistema e, se a última resposta for removida, marca a pergunta como não respondida.

#### **Gerenciamento de Tags**

-   **Buscar Todas as Tags de um Usuário**: Recupera todas as tags associadas a um usuário específico.
-   **Buscar Tag por Nome e Usuário**: Recupera uma tag específica de um usuário, identificada pelo nome da tag.
-   **Criar Tag**: Registra uma nova tag para um usuário. A tag não pode ser duplicada.
-   **Atualizar Tag**: Atualiza o nome de uma tag associada a um usuário. A tag não pode existir previamente para o mesmo usuário.


## Endpoints 📋

### Autenticação

1.  **Login**
    -   **URL**: `/auth/login`
    -   **Método**: `POST`
    -   **Descrição**: Autentica um usuário e gera um token JWT para acessar os recursos protegidos.
    -   **Requisição**:
        -   Corpo: `{ "username": "string", "password": "string" }`
    -   **Respostas**:
        -   `200 OK`: Retorna o token JWT e o papel (role) do usuário.
        -   `400 Bad Request`: Credenciais inválidas.

### Usuários

1.  **Buscar Usuário por ID**
    
    -   **URL**: `/user/{id}`
    -   **Método**: `GET`
    -   **Descrição**: Retorna as informações de perfil de um usuário específico baseado no ID.
    -   **Parâmetro**:
        -   `id`: ID do usuário.
    -   **Respostas**:
        -   `200 OK`: Retorna o perfil do usuário.
        -   `404 Not Found`: Usuário não encontrado.
2.  **Criar Usuário**
    
    -   **URL**: `/user/create`
    -   **Método**: `POST`
    -   **Descrição**: Cria um novo usuário no sistema.
    -   **Requisição**:
        -   Corpo: `{ "username": "string", "email": "string", "password": "string", "about": "string", "country": "string" }`
    -   **Respostas**:
        -   `201 Created`: Usuário criado com sucesso.
        -   `400 Bad Request`: Dados inválidos ou usuário já existente.
3.  **Atualizar Usuário**
    
    -   **URL**: `/user/{id}`
    -   **Método**: `PUT`
    -   **Descrição**: Atualiza todas as informações de um usuário específico.
    -   **Parâmetro**:
        -   `id`: ID do usuário.
    -   **Requisição**:
        -   Corpo: `{ "username": "string", "about": "string", "country": "string" }`
    -   **Respostas**:
        -   `200 OK`: Usuário atualizado com sucesso.
        -   `404 Not Found`: Usuário não encontrado.
4.  **Atualizar Parcialmente Usuário (Patch)**
    
    -   **URL**: `/user/{id}`
    -   **Método**: `PATCH`
    -   **Descrição**: Atualiza campos específicos do usuário.
    -   **Parâmetro**:
        -   `id`: ID do usuário.
    -   **Requisição**:
        -   Corpo: `{ "username": "string", "email": "string" }`
    -   **Respostas**:
        -   `200 OK`: Usuário atualizado com sucesso.
        -   `404 Not Found`: Usuário não encontrado.
5.  **Excluir Usuário**
    
    -   **URL**: `/user/{id}`
    -   **Método**: `DELETE`
    -   **Descrição**: Exclui um usuário do sistema.
    -   **Parâmetro**:
        -   `id`: ID do usuário.
    -   **Respostas**:
        -   `204 No Content`: Usuário excluído com sucesso.
        -   `404 Not Found`: Usuário não encontrado.

### **Perguntas**

1.  **Listar todas as perguntas do usuário**
    
    -   **URL**: `/question/all/{userId}`
    -   **Método**: `GET`
    -   **Descrição**: Retorna todas as perguntas feitas por um usuário específico.
    -   **Parâmetros**:
        -   `userId`: ID do usuário.
    -   **Respostas**:
        -   `200 OK`: Retorna uma lista de perguntas do usuário.
        -   `204 No Content`: Caso não existam perguntas para o usuário.
2.  **Obter pergunta por ID**
    
    -   **URL**: `/question/single/{id}`
    -   **Método**: `GET`
    -   **Descrição**: Retorna os detalhes de uma pergunta específica com base no ID.
    -   **Parâmetros**:
        -   `id`: ID da pergunta.
    -   **Respostas**:
        -   `200 OK`: Retorna os detalhes da pergunta.
        -   `404 Not Found`: Caso a pergunta não seja encontrada.
        -   `500 Internal Server Error`: Erro ao processar a requisição.
3.  **Buscar perguntas por título ou tag**
    
    -   **URL**: `/question/{id}`
    -   **Método**: `GET`
    -   **Descrição**: Retorna perguntas que correspondem a um título ou tag específico.
    -   **Parâmetros**:
        -   `id`: ID do usuário.
        -   `search`: Texto para busca de título ou tag.
    -   **Respostas**:
        -   `200 OK`: Retorna a lista de perguntas encontradas.
        -   `404 Not Found`: Caso não encontre nenhuma pergunta correspondente.
4.  **Criar uma nova pergunta**
    
    -   **URL**: `/question/`
    -   **Método**: `POST`
    -   **Descrição**: Cria uma nova pergunta no sistema.
    -   **Requisição**:
        -   Corpo: `{ "title": "string", "description": "string", "tags": ["tag1", "tag2"], "user": {"userId": 1} }`
    -   **Respostas**:
        -   `200 OK`: Retorna os dados da pergunta criada.
        -   `400 Bad Request`: Caso o título, descrição ou usuário sejam inválidos ou as tags não sejam encontradas.
        -   `500 Internal Server Error`: Erro ao processar a criação.
5.  **Responder a uma pergunta**
    
    -   **URL**: `/question/{questionId}/answer`
    -   **Método**: `POST`
    -   **Descrição**: Marca uma pergunta como respondida.
    -   **Parâmetros**:
        -   `questionId`: ID da pergunta.
    -   **Requisição**:
        -   Corpo: `{ "answer": "string" }`
    -   **Respostas**:
        -   `200 OK`: A pergunta é marcada como respondida.
        -   `404 Not Found`: Caso a pergunta não seja encontrada.
6.  **Adicionar tag a uma pergunta**
    
    -   **URL**: `/question/tag`
    -   **Método**: `POST`
    -   **Descrição**: Adiciona uma tag a uma pergunta existente.
    -   **Requisição**:
        -   Corpo: `{ "questionId": 1, "tagName": "tagName" }`
    -   **Respostas**:
        -   `200 OK`: Retorna a pergunta com a tag adicionada.
        -   `404 Not Found`: Caso a pergunta ou tag não seja encontrada.
        -   `400 Bad Request`: Caso a tag já exista na pergunta.
7.  **Atualizar pergunta**
    
    -   **URL**: `/question/`
    -   **Método**: `PUT`
    -   **Descrição**: Atualiza os dados de uma pergunta existente.
    -   **Requisição**:
        -   Corpo: `{ "questionId": 1, "title": "string", "description": "string", "tags": ["tag1", "tag2"] }`
    -   **Respostas**:
        -   `200 OK`: Retorna os dados da pergunta atualizada.
        -   `400 Bad Request`: Caso os dados fornecidos sejam inválidos.
        -   `404 Not Found`: Caso a pergunta não seja encontrada.
8.  **Deletar pergunta**
    
    -   **URL**: `/question/{questionId}`
    -   **Método**: `DELETE`
    -   **Descrição**: Exclui uma pergunta do sistema.
    -   **Parâmetros**:
        -   `questionId`: ID da pergunta.
    -   **Respostas**:
        -   `200 OK`: A pergunta foi excluída.
        -   `404 Not Found`: Caso a pergunta não seja encontrada.

### **Resposta**

1.  **Listar respostas de uma pergunta**
    
    -   **URL**: `/answers/{id}`
    -   **Método**: `GET`
    -   **Descrição**: Retorna todas as respostas associadas a uma pergunta específica.
    -   **Parâmetros**:
        -   `id`: ID da pergunta.
    -   **Respostas**:
        -   `200 OK`: Retorna uma lista de respostas da pergunta no formato `AnswerDto`.
        -   `404 Not Found`: Caso não existam respostas para a pergunta.
2.  **Criar uma nova resposta**
    
    -   **URL**: `/answers/`
    -   **Método**: `POST`
    -   **Descrição**: Cria uma nova resposta para uma pergunta existente.
    -   **Requisição**:
        -   Corpo: `{ "text": "string", "question": {"questionId": 1}, "user": {"userId": 1} }`
    -   **Respostas**:
        -   `200 OK`: Retorna a resposta criada.
        -   `400 Bad Request`: Caso a pergunta não exista.
        -   `403 Forbidden`: Caso o usuário não seja o autor da pergunta.
        -   `500 Internal Server Error`: Erro ao processar a criação.
3.  **Atualizar uma resposta existente**
    
    -   **URL**: `/answers/`
    -   **Método**: `PUT`
    -   **Descrição**: Atualiza o texto de uma resposta existente.
    -   **Requisição**:
        -   Corpo: `{ "answerId": 1, "text": "string" }`
    -   **Respostas**:
        -   `200 OK`: Retorna a resposta atualizada.
        -   `404 Not Found`: Caso a resposta não seja encontrada.
        -   `400 Bad Request`: Caso a requisição contenha dados inválidos.
4.  **Deletar uma resposta**
    
    -   **URL**: `/answers/{answerId}`
    -   **Método**: `DELETE`
    -   **Descrição**: Exclui uma resposta específica.
    -   **Parâmetros**:
        -   `answerId`: ID da resposta a ser excluída.
    -   **Respostas**:
        -   `204 No Content`: A resposta foi excluída com sucesso.
        -   `404 Not Found`: Caso a resposta não seja encontrada.
        -   `400 Bad Request`: Caso a pergunta associada à resposta não seja encontrada.
        -   `500 Internal Server Error`: Erro ao processar a exclusão.

### **Tags**

1.  **Listar todas as tags de um usuário**
    
    -   **URL**: `/tag/all/{id}`
    -   **Método**: `GET`
    -   **Descrição**: Retorna todas as tags associadas a um usuário específico.
    -   **Parâmetros**:
        -   `id`: ID do usuário.
    -   **Respostas**:
        -   `200 OK`: Retorna uma lista de tags do usuário.
        -   `204 No Content`: Caso o usuário não tenha tags associadas.
2.  **Obter tag por nome e usuário**
    
    -   **URL**: `/tag/{tagName}/{id}`
    -   **Método**: `GET`
    -   **Descrição**: Retorna uma tag específica pelo nome e ID do usuário.
    -   **Parâmetros**:
        -   `tagName`: Nome da tag.
        -   `id`: ID do usuário.
    -   **Respostas**:
        -   `200 OK`: Retorna a tag encontrada.
        -   `404 Not Found`: Caso a tag não seja encontrada para o usuário.
3.  **Criar uma nova tag**
    
    -   **URL**: `/tag/`
    -   **Método**: `POST`
    -   **Descrição**: Cria uma nova tag para um usuário.
    -   **Requisição**:
        -   Corpo: `{ "tagName": "string", "user": {"userId": 1} }`
    -   **Respostas**:
        -   `200 OK`: Retorna a tag criada.
        -   `400 Bad Request`: Caso o nome da tag seja nulo ou vazio, ou se o usuário for nulo, ou caso a tag já exista.
4.  **Atualizar uma tag existente**
    
    -   **URL**: `/tag/{tagName}/{id}`
    -   **Método**: `PUT`
    -   **Descrição**: Atualiza o nome de uma tag existente associada a um usuário.
    -   **Parâmetros**:
        -   `tagName`: Nome atual da tag.
        -   `id`: ID do usuário.
    -   **Requisição**:
        -   Corpo: `{ "tagName": "string" }`
    -   **Respostas**:
        -   `200 OK`: Retorna a tag atualizada.
        -   `400 Bad Request`: Caso o nome da tag seja nulo ou vazio, ou se a tag com o novo nome já existir.
        -   `404 Not Found`: Caso a tag não seja encontrada para o usuário.
