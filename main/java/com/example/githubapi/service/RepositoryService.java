package com.example.githubapi.service;

import com.example.githubapi.client.GithubClient;
import com.example.githubapi.client.dto.GithubRepoDto;
import com.example.githubapi.client.dto.GithubBranchDto;
import com.example.githubapi.exception.UserNotFoundException;
import com.example.githubapi.model.RepositoryResponse;
import com.example.githubapi.model.BranchResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RepositoryService {

    private final GithubClient githubClient;

    public RepositoryService(GithubClient githubClient) {
        this.githubClient = githubClient;
    }

    public List<RepositoryResponse> getRepositories(String username) {
        // pobierz repozytoria użytkownika
        List<GithubRepoDto> repos = githubClient.fetchUserRepositories(username);

        // odfiltruj forki i zamapuj na RepositoryResponse
        return repos.stream()
                .filter(repo -> !repo.isFork()) // tylko nie-forki
                .map(repo -> {
                    // pobierz branche dla repo
                    List<GithubBranchDto> branchDtos = githubClient.fetchBranches(
                            repo.getOwnerLogin(),
                            repo.getName()
                    );

                    // zamapuj branche
                    List<BranchResponse> branches = branchDtos.stream()
                            .map(b -> new BranchResponse(b.getName(), b.getLastCommitSha()))
                            .collect(Collectors.toList());

                    // zwróć model API
                    return new RepositoryResponse(
                            repo.getName(),
                            repo.getOwnerLogin(),
                            branches
                    );
                })
                .collect(Collectors.toList());
    }
}
