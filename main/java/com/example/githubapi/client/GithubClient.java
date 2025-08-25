package com.example.githubapi.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class GithubClient {

    private final RestTemplate restTemplate;
    private final String BASE_URL = "https://api.github.com";

    public GithubClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<GithubRepoDto> fetchUserRepositories(String username) {
        try {
            String url = BASE_URL + "/users/" + username + "/repos";
            GithubRepoDto[] response = restTemplate.getForObject(url, GithubRepoDto[].class);
            return List.of(response);
        } catch (RestClientException e) {
            // np. jeżeli API zwróci 404
            throw new UserNotFoundException("User " + username + " not found");
        }
    }

    public List<GithubBranchDto> fetchBranches(String owner, String repoName) {
        String url = BASE_URL + "/repos/" + owner + "/" + repoName + "/branches";
        GithubBranchDto[] response = restTemplate.getForObject(url, GithubBranchDto[].class);
        return List.of(response);
    }
}
