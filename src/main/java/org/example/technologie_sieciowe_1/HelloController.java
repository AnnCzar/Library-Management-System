package org.example.technologie_sieciowe_1;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class HelloController {
    @GetMapping("/helloWorld")
    public String helloworld(){
        return "Hello World";
    }
    @GetMapping("/helloSomeone")
    public String helloSomeone(@RequestParam(required = false) String name, @RequestParam("param2") String surname ){
        if (name == null){
            name = "John";
        }
        return "Hello " + name + " " +  surname;
    }
    @GetMapping("/hello/{name}")
    public  String hello(@PathVariable String name){
        return "Hello " + name + "!";
    }
    @GetMapping("/helloAdding")
    public String helloAdding(@RequestParam Integer number1, @RequestParam Integer number2){
        int result = number1 + number2;
        return "Result:" + result;
    }
}
