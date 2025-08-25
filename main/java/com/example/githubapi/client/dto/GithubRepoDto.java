package com.example.githubapi.client.dto;

public class GithubRepoDto {
    private String name;
    private boolean fork;
    private Owner owner;

    public String getName() { return name; }
    public boolean isFork() { return fork; }
    public String getOwnerLogin() { return owner.login; }

    static class Owner {
        private String login;
    }
}