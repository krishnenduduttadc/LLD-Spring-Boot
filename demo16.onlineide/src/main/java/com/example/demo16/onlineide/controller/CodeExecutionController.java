package com.example.demo16.onlineide.controller;

import com.example.demo16.onlineide.model.CodeRequest;
import com.example.demo16.onlineide.model.CodeResult;
import com.example.demo16.onlineide.service.CodeExecutionService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ide")
public class CodeExecutionController {

    private final CodeExecutionService service;

    public CodeExecutionController(CodeExecutionService service) {
        this.service = service;
    }

    @PostMapping("/run")
    public CodeResult runCode(@RequestBody CodeRequest request) {
        return service.execute(request);
    }
}
