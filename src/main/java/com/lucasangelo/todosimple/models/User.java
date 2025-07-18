package com.lucasangelo.todosimple.models;

// import java.util.ArrayList;
// import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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

    /**
     * 
     */
    public User() {
    }

    // lista de tarefas do usuario
    //private List<Task> tasks = new ArrayList<Task>();

    // construtores

    
}

