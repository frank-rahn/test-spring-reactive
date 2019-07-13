package de.rahn.spring.reactive.demo.web.demo;

import static org.springframework.http.MediaType.APPLICATION_STREAM_JSON;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import java.time.Duration;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class DemoHandler {

  public Mono<ServerResponse> getAllDemo(ServerRequest request) {
    Flux<String> text =
        Flux.range(1, 20).map(i -> " Demo -> " + i).delayElements(Duration.ofSeconds(2));

    return ok().contentType(APPLICATION_STREAM_JSON).body(text, String.class);
  }
}
