package com.revature.controller;

import org.apache.coyote.Response;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//@RestController is a combniation of @Controller and @ResponseBody
@RestController //This annotation makes a class a Bean, and sets every HTTP response to be JSON.
@RequestMapping(value ="/quesadilla")
public class QuesadillaController {

    //No more Javalin! No endpoint handlers, Spring MVC is very annotation/method driven

    //Let's make a method that returns a Quesadilla (just a mock String return)
    @GetMapping
    public ResponseEntity<String> getQuesadilla(){
        return ResponseEntity.status(200).body("Hey, here is your Quesadilla. Enjoy :)");
    }

    @GetMapping("/{amount}")
    public ResponseEntity<String> getSomeQuesadillas(@PathVariable int amount){
        getQuesadilla();
        //Let's practice some error handling on the user inputted "amount" variable
        if (amount < 1 || amount > 1000) {
            return ResponseEntity.status(400).body("Select more than zero quesadillas! (or less than 1000");
        }
        //if the user gave a valid number, then proceed with the success response.
        return ResponseEntity.ok("Here are your " + amount + " quesadillas.");
    }
    @PostMapping
    public ResponseEntity<String> returnQuesadillas(@RequestBody int amount) {
        //@RequestBody extracts the body of the request and maps it to a variable
        return ResponseEntity.accepted().body("Okay, we can take these " + amount + " quesadillas back :{.");
    }

    @PostMapping("/send/{userId}")
    //this method will let us send quesadillas to someone else
    public ResponseEntity<String> sendQuesadillas(@RequestBody int amount) {
        return ResponseEntity.accepted().body("Thanks for the " + amount  + " quesadillas!");
    }

}
