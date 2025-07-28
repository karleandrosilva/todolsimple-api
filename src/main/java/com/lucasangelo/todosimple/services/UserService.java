package com.lucasangelo.todosimple.services;

import org.springframework.stereotype.Service;

// Service: é a penúltima camada da aplicação API no Spring Boot. Acima dela está apenas o Controller, que é o ponto onde as requisições do front-end chegam.

// O fluxo funciona assim: Front-end → Controller → Service → Repository → Model → Banco de Dados.

// A camada Service representa a lógica de negócio da aplicação. Ela é importante para organizar melhor o código, aumentar a modularidade e a reusabilidade.

// Por exemplo: um método para buscar um usuário por ID pode ser usado em vários lugares, como em uma função de deletar usuário (que precisa buscar antes de remover). Assim, evitamos repetir código e centralizamos as regras de negócio.

// 1 - anotação do tipo da classe
@Service
public class UserService {
    
    // metodos e regras

    // PAREI EM 3:37
}
