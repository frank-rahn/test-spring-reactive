/*
 * Copyright (c) 2019-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.rahn.spring.reactive.demo.web.book;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
class BookRepository {

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
