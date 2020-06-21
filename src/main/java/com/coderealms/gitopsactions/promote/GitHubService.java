package com.coderealms.gitopsactions.promote;

import org.kohsuke.github.*;

import java.io.IOException;

public class GitHubService {

    private final GitHub github;

    private final GHRepository repository;

    public GitHubService(String gitHubToken, String repo) {
        try {
            github = new GitHubBuilder().withJwtToken(gitHubToken).build();
            repository = github.getRepository(repo);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public GHPullRequest createPullRequest(String sourceBranch, String targetBranch, String titleText) {
        try {
            return repository.createPullRequest(titleText, sourceBranch,
                    targetBranch, titleText);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

}
