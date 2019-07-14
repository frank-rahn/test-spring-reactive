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
