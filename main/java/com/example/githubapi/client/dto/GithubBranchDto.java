package com.example.githubapi.client.dto;

public class GithubBranchDto {

    private String name;
    private Commit commit;

    // potrzebne gettery (albo Lombok @Getter)
    public String getName() {
        return name;
    }

    public String getLastCommitSha() {
        return commit.sha;
    }

    // wewnętrzna klasa na commit
    public static class Commit {
        private String sha;

        public String getSha() {
            return sha;
        }
    }
}
