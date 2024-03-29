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
