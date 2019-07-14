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
