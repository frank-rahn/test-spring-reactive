package de.rahn.spring.reactive.demo.web.demo;

import java.time.Duration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.thymeleaf.spring5.context.webflux.ReactiveDataDriverContextVariable;
import reactor.core.publisher.Flux;

@Controller
class DemoController {

  @GetMapping(path = "/")
  String index(Model model) {
    model.addAttribute(
        "text",
        new ReactiveDataDriverContextVariable(
            Flux.range(1, 20).map(i -> " Demo -> " + i).delayElements(Duration.ofSeconds(2)), 1));

    return "index";
  }
}
