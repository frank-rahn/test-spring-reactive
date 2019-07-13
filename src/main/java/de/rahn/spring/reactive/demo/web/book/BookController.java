package de.rahn.spring.reactive.demo.web.book;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.http.MediaType.APPLICATION_STREAM_JSON_VALUE;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/books")
class BookController {

  private final BookRepository bookRepository;

  BookController(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  @GetMapping(path = "/async", produces = APPLICATION_STREAM_JSON_VALUE)
  Flux<Book> asyncron() {
    return bookRepository.findAll();
  }

  @GetMapping(path = "/sync", produces = APPLICATION_JSON_UTF8_VALUE)
  Mono<List<Book>> synchron() {
    return bookRepository.findAll().collectList();
  }
}
