package com.lucasangelo.todosimple.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

// Anotações do spring: @

@Entity // 1 - Declara que será uma entidade e que será mapeada para uma tabela no Banco de dados 
@Table (name = User.TABLE_NAME) // 3 - Defini que é para criar a tabela no BD e que especifica o nome da tabela com o valor da constante TABLE_NAME que é "user"
public class User {

    // 11 - Crio duas interfaces para validar, se não é notnull e notempty e o size
    public interface CreateUser {}
    public interface UpdateUser {}
    
    public static final String TABLE_NAME = "user"; // 2 - Para garantir que de fato o nome da tabela será "user"

    // Anotações JPA e atributos do usuario

    @Id // 6 - Para definir que é um id, e marca como uma chave primaria da tabela
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 7 - Uma estratégia para gerar um número no BD, sendo um valor unico e auto incremento para o id
    @Column(name = "id", unique = true) // 8 - Garante que os valores sejam únicos (sem repetições). É opcional, mas é bom deixar para garantir
    private Long id; //5 - Defini o id como Long, pois suportam muitos registros sem risco de estouro

    // 16 - Garante que a senha não seja mostrada nas respostas da api via json
    @JsonProperty(access = Access.WRITE_ONLY)

    // 10 -  Adiciono a dependência "spring-boot-starter-validation" no pom.xml para ter validações automáticas nos campos, para nao deixar o erro cair no BD. E ela faz com que posso usar o notnull e notempty
    @Column(name = "username", length = 100, nullable = false, unique = true ) // 9 - Define que é uma coluna: com no máximo 100 caracteres, não pode ser nulo e deve ser única (sem repetições
    @NotNull(groups = CreateUser.class) // Quando criar um usuario, não pode ser nulo
    @NotEmpty(groups = CreateUser.class) // Quando criar um usuario, não pode por uma string vazia
    @Size(groups = CreateUser.class, min = 2, max = 100) // Quando criar um usuario, tera um minimo de caractere e o maximo
    private String username;

    @Column(name = "password", length = 60, nullable = false)
    @NotNull(groups = {CreateUser.class, UpdateUser.class}) // uso um array
    @NotEmpty(groups = {CreateUser.class, UpdateUser.class}) 
    @Size(groups = {CreateUser.class, UpdateUser.class}, min = 8, max = 60)

    private String password;

    //18 - lista de tarefas do usuario
    @OneToMany(mappedBy = "user") // um usuario pode ter muitas task (1:n),  mapeado pelo atributo 'user' em Task
    private List<Task> tasks = new ArrayList<Task>();

    // construtores

    // 12 - construtor padrao (necessário para o JPA instanciar a entidade)
    public User() {
    }

    // construtor com todos campos da entidade
    public User(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    // 13 - getters e setters (acessores e modificadores dos atributos)

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // getters e setter do task

    @JsonIgnore // 17 - Ignora a lista de tarefas quando serializar o usuário para JSON, pois não preciso que o front veja as tarefas do usuário quando buscar o usuário 
    public List<Task> getTasks() {
        return this.tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
    
    // 14 - Define quando dois objetos User são considerados iguais. Considera dois objetos User iguais se eles tiverem o mesmo id, username e password.
    @Override

    public boolean equals (Object obj) {
        if (obj == this)
            return true; // Se os dois objetos forem exatamente o mesmo na memória, então são iguais
        if (obj == null)
            return false; // Se o objeto comparado for nulo, eles não são iguais
        if (!(obj instanceof User)) 
            return false; // Se o objeto não for da classe User, eles não são comparáveis
        User other = (User) obj; // transforma o Object genérico em um User
        if (this.id == null)
            if (other.id != null) 
                return false;
            else if (!this.id.equals(other.id))
                return false;
        return Objects.equals(this.id, other.id) && Objects.equals(this.username, other.username)
                && Objects.equals(this.password, other.password);
    }
    
    // 15 - Garante que objetos iguais (pelo equals()) funcionem corretamente em coleções como HashSet. É uma estrtura padrão "receita pronta"
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( this.id == null ? 0: this.id.hashCode());
        return result;
    }
}

