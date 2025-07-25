package com.lucasangelo.todosimple.repositories;

// Repository:
// É uma interface usada pelo Spring para facilitar o acesso ao BD. Permite fazer operações como salvar, buscar, deletar e atualizar dados de forma automática.
// Está sempre relacionada a um Model (entidade), como User ou Task.
// O Spring Data JPA fornece métodos prontos como: save(), findAll(), findById(), deleteById(), etc.
// Também é possível criar consultas personalizadas só com o nome do método (ex: findByEmail()).
// Isso evita a necessidade de escrever SQL manual como: SELECT * FROM ...
// Até agora, só tínhamos criado as tabelas com os Models.
// Com o Repository, conseguimos consultar e manipular os dados no banco de forma prática e automatizada.

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lucasangelo.todosimple.models.User;

// 1 - Não é uma classe, e sim uma interface, pois nela só definimos o nome dos métodos, o tipo de retorno e os parâmetros.

@Repository // 2 - @Repository é a anotação que indica que esta interface é um componente de acesso a dados (camada de repositório).

// 3 - Para fazer a conexão com o banco, estende a interface JpaRepository, passando como parâmetros:a entidade que será gerenciada (User) e o tipo do ID da entidade (Long).
public interface  UserRepository extends JpaRepository<User, Long> {
    
    // Não é necessário escrever os métodos aqui, pois a interface JpaRepository já fornece vários métodos prontos para operações básicas como salvar, buscar, atualizar e deletar dados.

}
