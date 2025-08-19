// AULA 11

// Controller: é a camada da API responsável por receber as requisições do cliente (por exemplo, do front-end) e envia as respostas.
// O Controller chama a camada Service, que contém a lógica de negócio, e o Service se comunica com o Repositorie, que faz o acesso ao banco de dados.

package com.lucasangelo.todosimple.controllers;

import java.net.URI;

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

import com.lucasangelo.todosimple.models.User;
import com.lucasangelo.todosimple.models.User.CreateUser;
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

    // (GetMapping) - para BUSCAR o usuário pelo id, o front vai fazer uma requisição GET para a rota /user/{id} 
    @GetMapping("/{id}") // 6 - Depois do /user, tem que ter o id do usuario, coloca as chaves: "/{id}", para delimitar que é uma variável.
    
    // 7 - ResponseEntity<User>: entidade de resposta do tipo usuario, que é o que vai ser retornado para o front, e o id do usuário que está sendo buscado. Assim, ficará tratado
    public ResponseEntity<User> findById(@PathVariable Long id) { // o spring não reconhece que está relacionado com o mesmo id, então adiciona uma anotação dentro da função: @PathVariable, isso fará com que o spring saiba que o "id" da URL vai vir parar aqui nessa variável.

        User obj = this.userService.findById(id); // 8 - Chama o serviço de usuário para buscar o usuário pelo id.

        return ResponseEntity.ok().body(obj); // 9 - Retorna uma resposta HTTP, que é: 200 (OK) com o usuário encontrado. (Significa que não deu nenhum erro, e o usuário foi encontrado)

        // .body(obj) - onde vai chegar o dado. Define o corpo da resposta, que será o objeto User encontrado
    }

    // - MÉTODO CREATE - 
    @PostMapping // 11 - significa que é para CRIAR um dado (inserir um novo usuário) - (responde a requisições HTTP POST)
    @Validated(CreateUser.class) // 12 - Valida os dados do usuário, aplicando as regras de validação definidas na interface CreateUser, que define as regras de validação para criação de usuário.

    // 10 - create (vai ser void, pois só está criando o usuário, não vai retornar dados)

    public ResponseEntity<Void> create(@Valid @RequestBody User obj) { //12 - @Valid : garante que os dados recebidos no body  (corpo da requisição) sejam validados.

        // @RequestBody: indica que o objeto User será enviado no corpo da requisição.
        
        // Observação: Somente CREATE e UPDATE enviam dados no body.
        // Métodos GET e DELETE não envia dados no body
 
        this.userService.create(obj); // 13 - Chama o serviço de usuário para executar a lógica de criação do novo usuário.

        // 15 - URI: Uniform Resource Identifier, é um identificador único para o recurso criado. No caso, o usuário que foi criado. Assim, vai mandar para a rota /user/{id} do usuário recém-criado.

        // toUri() : converte o resultado em um objeto URI.
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri(); // qual a rota - onde que encontra esse usuario que foi criado, ai tem que mostrar a rota/request para encontrar o usuario: 

        // servletUriComponentsBuilder: é uma classe que constrói URIs de forma fácil e segura.

        // fromCurrentRequest() : build que pega o contexto da requisição atual, ou seja, a URL que foi chamada para criar o usuário.

        // path("/{id}") : adiciona o caminho "/{id}" à URL, onde "{id}" será substituído pelo ID do usuário recém-criado.

        // buildAndExpand(obj.getId()) : substitui "{id}" pelo ID do usuário que foi criado, obtido através do método getId() do objeto User.

        //.toUri() : converte o resultado em um objeto URI, que é o identificador único do recurso criado.

        // Retorna uma resposta HTTP 201 (Created) indicando que o usuário foi criado com sucesso.
        // O método created() indica que a criação foi bem-sucedida e build() constrói a resposta sem corpo.

        return ResponseEntity.created(uri).build(); // 14 - Retorna uma resposta HTTP 201 (Created) indicando que o usuário foi criado com sucesso. O método created() indica que a criação foi bem-sucedida, e build() constrói a resposta sem corpo.
    }

    // MÉTODO UPDATE
    // é um tipo void, pois está atualizando o usuário, não vai retornar nada.
    @PutMapping("/{id}") // 16 - é usado pra atualizar tudo
    @Validated(User.UpdateUser.class) // 17 - validação de uptade que toda essa classe tem 
    public ResponseEntity<Void> update(@Valid @RequestBody User obj, @PathVariable Long id) { // tem que receber o id também 
        obj.setId(id); // 18 - Define o ID do usuário que está sendo atualizado, garantindo que o objeto User tenha o ID correto.

        this.userService.update(obj); // 19 - Chama o serviço de usuário para executar a lógica de atualização do usuário.

        return ResponseEntity.noContent().build(); // 20 - Retorna uma resposta HTTP 204 (No Content) indicando que a atualização foi bem-sucedida, mas não há conteúdo para retornar.
    }

    // MÉTODO DELETE
    @DeleteMapping("/{id}") // 21 - é usado para deletar um usuário, o front vai fazer uma requisição DELETE para a rota /user/{id}, onde {id} é o ID do usuário a ser deletado.
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.userService.delete(id); 
        return ResponseEntity.noContent().build(); 
    }
    
}
