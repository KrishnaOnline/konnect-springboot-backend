package com.krishnavamshi.konnect.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

class HomeResponse {
    private Boolean success;
    private String message;
    public HomeResponse(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }
    public Boolean getSuccess() {
        return success;
    }
    public void setSuccess(Boolean success) {
        this.success = success;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}

@RestController
public class HomeController {
    @GetMapping("/")
    public HomeResponse homeControllerHandler() {
        return new HomeResponse(true, "Konnect Server is Up and Running...");
    }
}