package com.example.githubapi.controller;

import com.example.githubapi.exception.UserNotFoundException;
import com.example.githubapi.model.ErrorResponse;
import com.example.githubapi.model.RepositoryResponse;
import com.example.githubapi.service.RepositoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/repositories")
public class RepositoryController {

    private final RepositoryService repositoryService;

    public RepositoryController(RepositoryService repositoryService) {
        this.repositoryService = repositoryService;
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> getRepositories(@PathVariable String username) {
        try {
            List<RepositoryResponse> repos = repositoryService.getRepositories(username);
            return ResponseEntity.ok(repos);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(404)
                    .body(new ErrorResponse(404, e.getMessage()));
        }
    }
}
