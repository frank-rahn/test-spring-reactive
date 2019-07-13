package de.rahn.spring.reactive.demo.web.book;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class BookRepository {

  private final List<Book> books = new ArrayList<>();

  @PostConstruct
  void setup() {
    books.add(new Book(100, "Java Kompendium"));
    books.add(new Book(200, "Rust Kompendium"));
    books.add(new Book(300, "Kotlin Kompendium"));
    books.add(new Book(400, "Spring Kompendium"));
    books.add(new Book(500, "Vue Kompendium"));
    books.add(new Book(600, "Docker Kompendium"));
    books.add(new Book(700, "Kubernetes Kompendium"));
    books.add(new Book(800, "JavaScript Kompendium"));
    books.add(new Book(900, "HTML5 Kompendium"));
    books.add(new Book(1000, "Bootstrap Kompendium"));
  }

  public Mono<Book> findById(long id) {
    return Mono.justOrEmpty(books.stream().filter(book -> book.getId() == id).findFirst().get());
  }

  public Flux<Book> findAll() {
    return Flux.fromIterable(books).delayElements(Duration.ofSeconds(2));
  }
}
