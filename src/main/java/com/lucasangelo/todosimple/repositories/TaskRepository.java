package com.lucasangelo.todosimple.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lucasangelo.todosimple.models.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    
    // Exemplo de busca personalizada no banco de dados usando Spring. Este método retorna uma lista de tarefas (tasks) de um usuário com base no ID do usuário

    // O Spring cria automaticamente a query com base no nome do método (findByUser_Id)
    List<Task> findByUser_Id(Long id); // "User" é o nome do atributo na entidade Task

} 
