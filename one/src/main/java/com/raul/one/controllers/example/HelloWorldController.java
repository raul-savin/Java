package com.raul.one.controllers.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

  @GetMapping("/")
  public String helloWorld() {
    return "hello world!";
  }
}
