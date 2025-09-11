package com.lucasangelo.todosimple.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// notação para avisar ao spring que é pra iniciar a classe junto com a aplicação: @Configuration

@Configuration
@EnableWebMvc // Ativa configurações extras do Spring MVC, permitindo personalizar o comportamento padrão.
public class WebConfig implements WebMvcConfigurer{
    
    // A classe implementa WebMvcConfigurer para que possamos sobrescrever métodos de configuração do Spring MVC.

    // Configuração de CORS (Cross-Origin Resource Sharing).
    // CORS controla quais domínios externos podem acessar a API.

    public void addCorsMappings(CorsRegistry registry){

        // qualquer requisição que vinher de fora, vai liberar apartir desta rota: /** (depois do 8080, tudo está liberado) 

        registry.addMapping("/**"); 

          // Permite requisições CORS para qualquer rota da aplicação (/**)
        // Exemplo: se a API está em localhost:8080, qualquer rota depois disso (/user, /task, etc.)
        // poderá ser acessada de outras origens (como o front-end em localhost:3000).

    }
}
