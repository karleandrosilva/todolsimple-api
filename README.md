# Aula 01 — Configuração da Aplicação Spring Boot

## 📁 Estrutura de Pastas Criada em `src/main/java/todosimple`

Criei os seguintes pacotes:

- **`models`**: Contém os modelos de dados, como o modelo de `Usuário` e suas `Tarefas`.
- **`controllers`**: Responsável por receber e tratar requisições HTTP.
- **`services`**: Camada intermediária entre `controllers` e `models`, onde ficam as regras de negócio e funções reutilizáveis.
- **`repositories`**: Interfaces do Spring que lidam diretamente com o banco de dados. São usadas para salvar, buscar e deletar dados com Spring Data JPA.

---

## 🎨 Configuração de Terminal com Saída Colorida

- No arquivo `.vscode/settings.json`, adicionei:

```json
"terminal.integrated.defaultProfile.windows": "Command Prompt"
```

- No arquivo `src/main/resources/application.properties`, adicionei:

```
spring.output.ansi.enabled=ALWAYS
```

Isso fará com que as mensagens no terminal apareçam coloridas, facilitando a distinção entre erros, avisos e mensagens de sucesso.

---

## 💾 Configuração do Banco de Dados no `application.properties`

1. **Driver do MySQL**
   ```properties
   spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
   ```

2. **URL de Conexão**
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/todosimple?createDatabaseIfNotExist=true
   ```

3. **Credenciais do Banco**
   ```properties
   spring.datasource.username=root
   spring.datasource.password=root
   ```

4. **Criação e Atualização Automática do Banco**
   ```properties
   spring.jpa.hibernate.ddl-auto=update
   ```

5. **Mostrar Comandos SQL no Terminal**
   ```properties
   spring.jpa.show-sql=true
   ```

---

# Aula 02 — Criando o Model de User

# Aula 12 — Usando o postman 

