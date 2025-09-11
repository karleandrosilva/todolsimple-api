package com.lucasangelo.todosimple.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// notação para avisar ao spring que é pra iniciar a classe junto com a aplicação: @Configuration

@Configuration
@EnableWebMvc // vai trazerc algumas configurações
public class WebConfig implements WebMvcConfigurer{
    
    // adiciona implements: WebMvConfigurer

    // método

    public void addCorsMappings(CorsRegistry registry){

        registry.addMapping("/**"); // qualquer requisição que vinher de fora, vai liberar apartir desta rota: /** (depois do 8080, tudo está liberado) 

    }
}
