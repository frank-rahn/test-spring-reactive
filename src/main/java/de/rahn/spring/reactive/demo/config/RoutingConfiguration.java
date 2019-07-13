package de.rahn.spring.reactive.demo.config;

import de.rahn.spring.reactive.demo.web.book.BookHandler;
import de.rahn.spring.reactive.demo.web.demo.DemoHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/** Die Konfiguration für das Routing. */
@Configuration
class RoutingConfiguration {

  @Bean
  RouterFunction<ServerResponse> rootDemo(DemoHandler demoHandler) {
    return RouterFunctions.route().GET("/demo", demoHandler::getAllDemo).build();
  }

  @Bean
  RouterFunction<ServerResponse> rootBooks(BookHandler bookHandler) {
    return RouterFunctions.route()
        .GET("/books/id/{id}", bookHandler::getBookById)
        .GET("/books", bookHandler::getAllBooks)
        .build();
  }
}
