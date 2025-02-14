package com.springapp.spring_api.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class HelloController {
  
  @GetMapping("/hello")
  public String hello() {
    try{
      return "Hello TSC team";
    }catch(Exception e){
      return e.toString();
    }
  }
}
