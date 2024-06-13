package com.portfolio.microservices.product;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfiguration {
    
    @Bean
    public OpenAPI defineOpenAPi(){
        Server server = new Server();
        server.setUrl("http://localhost:8080");
        server.setDescription("Development server");

        Contact contact = new Contact();
        contact.setName("Keith Mutuku");
        contact.setEmail("karirumutuku@gmail.com");

        Info info = new Info()
                .title("Product Service API")
                .description("API for managing products")
                .version("1.0.0")
                .contact(contact);
        
        return new OpenAPI().info(info).addServersItem(server);
    }
}
