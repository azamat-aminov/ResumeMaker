package uz.azamat.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ResumeController {

    @GetMapping
    public String getPage() {
        return "person";
    }
}
