// CRUD DA TASK
package com.lucasangelo.todosimple.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lucasangelo.todosimple.models.Task;
import com.lucasangelo.todosimple.services.TaskService;
import com.lucasangelo.todosimple.services.UserService;

@RestController
@RequestMapping("/task") // 1 - Define a rota base para este Controller, que será "/task".
@Validated
public class TaskController {

    // buscar todas as tasks de um usuario
    // no controller de task, retorna os objetos de task


    @Autowired // 3 - Injeção de dependência automatica do TaskService, que é responsável pela lógica de negócio relacionada às tarefas.
    private TaskService taskService; // 2 - Declaração do serviço de tarefa, que será injetado pelo Spring.


    // AULA 13 (fazer com que mostre que esse usuario não existe)
    @Autowired 
    private UserService userService; //

    @GetMapping("/{id}") // 6 - Mapeia requisições GET para a rota "/task", que será usada para buscar todas as tarefas.

    // MÉTODO DE BUSCAR PELO ID DA TASK
    public ResponseEntity<Task> findById(@PathVariable Long id) {
        Task obj = this.taskService.findById(id); // 4 - Chama o serviço de tarefa para buscar a tarefa pelo id
        return ResponseEntity.ok().body(obj); // 5 - Retorna uma resposta HTTP, que é: 200 (OK) com a tarefa encontrada.
    }

    // MÉTODO DE BUSCAR TODAS AS TASKS DO USUARIO PELO ID
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Task>> findAllByUserId(@PathVariable Long userId) {
        userService.findById(userId); // AULA 13 - verifica se o usuário existe. Se não existir, lança exceção e interrompe a execução.
        List<Task> objs = this.taskService.findAllByUserId(userId);
        return ResponseEntity.ok().body(objs);
    }

    // MÉTODO CREATE
    @PostMapping
    @Validated
    public ResponseEntity<Void> create(@Valid @RequestBody Task obj) {

        this.taskService.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();

    }

    // MÉTODO UPDATE
    @PutMapping("/{id}")
    @Validated
    public ResponseEntity<Void> update(@Valid @RequestBody Task obj, @PathVariable Long id) {

        obj.setId(id);
        this.taskService.update(obj);
        return ResponseEntity.noContent().build();
    }

    // MÉTODO DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.taskService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
