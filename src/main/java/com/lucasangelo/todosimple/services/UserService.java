package com.lucasangelo.todosimple.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lucasangelo.todosimple.models.User;
import com.lucasangelo.todosimple.repositories.UserRepository;

// Service: é a penúltima camada da aplicação API no Spring Boot. Acima dela está apenas o Controller, que é o ponto onde as requisições do front-end chegam.

// O fluxo funciona assim: Front-end → Controller → Service → Repository → Model → Banco de Dados.

// A camada Service representa a lógica de negócio da aplicação. Ela é importante para organizar melhor o código, aumentar a modularidade e a reusabilidade.

// Por exemplo: um método para buscar um usuário por ID pode ser usado em vários lugares, como em uma função de deletar usuário (que precisa buscar antes de remover). Assim, evitamos repetir código e centralizamos as regras de negócio.

// 1 - anotação do tipo da classe
@Service
public class UserService {
    // metodos e regras
    
    // os services provalvemente vao se comunicar com os repositories para buscar e manipular dados no banco de dados
    // importar os repository atributos
    @Autowired // Injeção de dependência: o Spring cria e injeta automaticamente uma instância do repositório,basicamente é meio que um construtor automático do Spring Boot
    private UserRepository userRepository;

    // getters e setters não são necessários aqui

    // funções basicas CRUD

    public User findById(Long id) {
        // Função para buscar um usuário pelo ID

        // Utiliza Optional para evitar NullPointerException caso o usuário não seja encontrado.

        Optional<User> user = this.userRepository.findById(id); // Se não encontrar o usuário no banco, o método retorna um Optional vazio (em vez de null).

        return user.orElseThrow(() ->  new RuntimeException(
            "Usuário não encontrado! ID: " + id + ", Tipo: " + User.class.getName())); // Se o usuário estiver vazio, lança uma exceção
    };

    @Transactional // Utiliza para fazer alguma persistência no bd, como criar e/ou atualizar (fazer um isert). É bom, para ter controle, ele vai salvar dados em memoria, para garantir que estão lá. E se algo der errado, todas as alterações feitas no banco serão revertidas.
    // Garante que todas as operações dentro do método sejam executadas em uma única transação.Se alguma falhar, tudo será revertido, mantendo a integridade do banco de dados.

    public User create(User obj) {
        // Função para criar usuario
 
        // garante que está criando um novo usuario
        obj.setId(null); // Define o ID como null para garantir que o usuário será criado como um novo registro (INSERT)

        obj = this.userRepository.save(obj); // Salva o usuário no bd 
        return obj; // Retorna o usuário criado
    }
    
    @Transactional // pois vai fazer uma inserção no bd
    public User update(User obj) {
        // funcão para atualizar usuario
        // neste caso, apenas a senha será atualizada

        // cria um novo objeto para atualizar alguns dados, pois o ID não pode ser alterado
        User newObj = findById(obj.getId()); // Busca o usuário existente pelo ID informado no objeto obj

        newObj.setPassword(obj.getPassword()); // Atualiza a senha do usuário que foi passado como parâmetro

        return this.userRepository.save(newObj); // Salva as alterações no banco de dados e retorna o usuário atualizado
    }
  
    public void delete(Long id) {
        // Função para deletar usuário

        // Verifica se o usuário existe antes de tentar deletar
        findById(id); // Se não encontrar, lança uma exceção

        // se deletar uma entidade que tem relacionamentos com outras entidades, tem chances de dar erro. Por isso, é bom verificar se tem relacionamentos antes de deletar
        try {
            this.userRepository.deleteById(id); // Deleta o usuário pelo ID, se ele existir
        } catch (Exception e) {
            throw new RuntimeException("Não é possível deletar pois há entidades relacionadas!");
        }
    }

}
