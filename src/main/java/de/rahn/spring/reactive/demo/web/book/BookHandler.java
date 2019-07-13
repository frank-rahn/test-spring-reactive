package de.rahn.spring.reactive.demo.web.book;

import static org.springframework.http.MediaType.APPLICATION_STREAM_JSON;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class BookHandler {

  private final BookRepository bookRepository;

  BookHandler(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  public Mono<ServerResponse> getBookById(ServerRequest request) {
    long id = Long.parseLong(request.pathVariable("id"));

    return ok().contentType(APPLICATION_STREAM_JSON).body(bookRepository.findById(id), Book.class);
  }

  public Mono<ServerResponse> getAllBooks(ServerRequest request) {
    return ok().contentType(APPLICATION_STREAM_JSON).body(bookRepository.findAll(), Book.class);
  }
}
