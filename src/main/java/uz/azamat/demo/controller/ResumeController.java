package uz.azamat.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ResumeController {

    @GetMapping
    public String getPage() {
        return "login";
    }

    @PostMapping("/person")
    public String getForms(){
        return "person";
    }
}
