package com.learning.tdd.springrest.controller;

import org.apache.catalina.User;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import java.net.*;

@Controller
public class DemoController {

    @GetMapping("/sayHello")
    public ResponseEntity sayHello(@RequestParam(defaultValue = "There") String firstName) {
        if (isValid(firstName)) {
            return ResponseEntity.ok("Hello " + firstName + "!");
        }

        return ResponseEntity.badRequest().body("First name needs to be alphabets");
    }

    @PostMapping("/sayHello")
    public ResponseEntity createRecord(@RequestParam String firstName) {
        if (isValid(firstName)) {
            URI defaultUri = URI.create("http://google.com");
            return ResponseEntity.created(defaultUri).body("Record created for "+firstName+"!");
        }

        return ResponseEntity.badRequest().body("First name needs to be valid");
    }

    private boolean isValid(@RequestParam(defaultValue = "There") String firstName) {
        return !firstName.trim().isEmpty() && firstName.matches("[A-Z][a-z]*");
    }
    
    @PutMapping("/sayHello/{lastName}")
    public ResponseEntity updateRecord(@PathVariable(value="lastName") String lastName){
    	URI defaultUri = URI.create("http://google.com");
		return ResponseEntity.created(defaultUri).body("Record updated for "+ lastName );
    	
    }
    
    @DeleteMapping("sayHello/{firstName}")
    public ResponseEntity deleteRecord(@PathVariable("firstName") String firstName) {
    	URI defaultUri = URI.create("http://google.com");
		return ResponseEntity.created(defaultUri).body("FirstName is delete");
    	
    }
    
}