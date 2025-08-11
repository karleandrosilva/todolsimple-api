// AULA 11

// Controller: é a camada da API responsável por receber as requisições do cliente (por exemplo, do front-end) e envia as respostas.
// O Controller chama a camada Service, que contém a lógica de negócio, e o Service se comunica com o Repositorie, que faz o acesso ao banco de dados.

package com.lucasangelo.todosimple.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucasangelo.todosimple.models.User;
import com.lucasangelo.todosimple.services.UserService;

@RestController // 1 - Indica que esta classe é um Controller do Spring voltado para APIs REST.
// no UserController, os metódos que tem, tudo é para o user.

// Todas as requisições que começarem com "/user" serão mapeadas para os métodos desta classe.
@RequestMapping("/user") // 2 - Define a rota base para este Controller.
@Validated// 3 - Ativa o suporte à validação de dados (@NotNull, @Size, etc.).
public class UserController {

    @Autowired // 4 - Injeção de dependência automatica do UserService, que é responsável pela lógica de negócio relacionada aos usuários.
    private UserService userService; // 5 - Declaração do serviço de usuário, que será injetado pelo Spring.
 
    // receber os dados do front, que vai cair na api e chegar no controller

    // funcao de buscar por id, quando fazer uma busca por id, vai cair aqui no controller

    // exemplo: localhost:8080/user/1

    // para buscar o usuário pelo id, o front vai fazer uma requisição GET para a rota /user/{id}
    @GetMapping("/{id}") // 6 - Depois do /user, tem que ter o id do usuario, coloca as chaves: "/{id}", para delimitar que é uma variável.
    
    // 7 - ResponseEntity<User>: entidade de resposta do tipo usuario, que é o que vai ser retornado para o front, e o id do usuário que está sendo buscado. Assim, ficara tratado
    public ResponseEntity<User> findById(@PathVariable Long id) { // o spring não reconhece que esta relacionado com o mesmo id, então adiciona uma anotação dentro da função: @PathVariable, isso fará com que o spring saiba que o "id" da URL vai vir parar aqui nessa variável.

        User obj = this.userService.findById(id); // 8 - Chama o serviço de usuário para buscar o usuário pelo id.

        return ResponseEntity.ok().body(obj); // 9 - Retorna uma resposta HTTP, que é: 200 (OK) com o usuário encontrado. (Significa que não deu nenhum erro, e o usuário foi encontrado)
        // .body(obj) define o corpo da resposta, que será o objeto User encontrado
    }
    
}
