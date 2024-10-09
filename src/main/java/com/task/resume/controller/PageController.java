package com.task.resume.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String showUploadForm() {
        return "upload"; // This will look for src/main/resources/templates/upload.html
    }
}
