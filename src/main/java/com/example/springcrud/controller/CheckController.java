package com.example.springcrud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CheckController {
    
    @GetMapping("/check")
    public String getQueryParam(String param) {
        return param;
    }
}
