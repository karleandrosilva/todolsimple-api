package com.lucasangelo.todosimple.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = Task.TABLE_NAME)

public class Task {
    public static final String TABLE_NAME = "task";
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    // essa task pertence a um usuario
    @ManyToOne // defini que varias tarefas podem ser de um usuario (relação: n:1)
    @JoinColumn(name = "user_id", nullable = false, updatable = false) // cria uma coluna "user_id" na tabela de Task, fazendo referência à chave primária da tabela User (chave estrangeira)
    private User user;


    @Column(name = "description", length = 250, nullable = false)
    @NotNull
    @NotEmpty
    @Size(min = 1, max = 250)
    private String description;

    // Gerando automaticamente

    public Task() {
    }

    public Task(Long id, User user, String description) {
        this.id = id;
        this.user = user;
        this.description = description;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Task id(Long id) {
        setId(id);
        return this;
    }

    public Task user(User user) {
        setUser(user);
        return this;
    }

    public Task description(String description) {
        setDescription(description);
        return this;
    }

    // equals e hashcode

    @Override
    public boolean equals (Object obj) {
        if (obj == this)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Task)) 
            return false;
        Task other = (Task) obj; 
        if (this.id == null)
            if (other.id != null) 
                return false;
            else if (!this.id.equals(other.id))
                return false;
        return Objects.equals(this.id, other.id) && Objects.equals(this.user, other.user)
                && Objects.equals(this.description, other.description);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( this.id == null ? 0: this.id.hashCode());
        return result;
    }

}
