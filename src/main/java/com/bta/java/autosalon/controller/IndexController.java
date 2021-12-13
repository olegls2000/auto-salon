package com.bta.java.autosalon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

  @GetMapping("/")
  public String getRootView() {
    return "index";
  }

  @GetMapping("/index.html")
  public String getIndexView() {
    return "index";
  }
}
