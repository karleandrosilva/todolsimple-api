// AULA 10
package com.lucasangelo.todosimple.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucasangelo.todosimple.models.Task;
import com.lucasangelo.todosimple.models.User;
import com.lucasangelo.todosimple.repositories.TaskRepository;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserService userService;

    // procura tarefa por ID
    public Task findById(Long id) {
        Optional<Task> task = this.taskRepository.findById(id);
        return task.orElseThrow(() ->  new RuntimeException(
            "Tarefa não encontrada! ID: " + id + ", Tipo: " + Task.class.getName()));
    }


    //create
    @Transactional // Garante que a operação de criação será feita em uma transação, garantindo a integridade dos dados.
    public Task create(Task obj) {
        User user = this.userService.findById(obj.getUser().getId()); // Busca o usuário associado à tarefa
        obj.setId(null);
        obj.setUser(user); 
        obj = this.taskRepository.save(obj);
        return obj; // Salva a tarefa no banco de dados
    }

    //update
    @Transactional
    public Task update(Task obj) {
        Task newObj = this.findById(obj.getId()); 
        newObj.setDescription(obj.getDescription()); //Atualiza a descrição da tarefa
        return this.taskRepository.save(newObj); // Salva as alterações no banco de dados
    }

    //delete
    @Transactional
    public void delete(Long id) {
        findById(id); 
        try {
            this.taskRepository.deleteById(id); 
        } catch (Exception e) {
            throw new RuntimeException("Não é possível deletar pois há entidades relacionadas!");
        }
    }

    
}
