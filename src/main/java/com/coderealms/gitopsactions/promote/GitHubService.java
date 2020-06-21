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

    public GHRef createBranch(String branch, String fromBranch) {
        try {
            String fromSha = repository.getBranch(fromBranch).getSHA1();
            return repository.createRef("refs/heads/" + branch, fromSha);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public GHContentUpdateResponse propagateVersionFile(String filePath, String versionContent, String branch) {
        try {
            return repository.getFileContent(filePath, branch).update(versionContent, "Updated version file - " + versionContent, branch);
        } catch (IOException e) {
            return createVersionFile(filePath, versionContent, branch);
        }
    }

    public GHContentUpdateResponse createVersionFile(String filePath, String versionContent, String branch) {
        try {
            return repository.createContent()
                    .branch(branch)
                    .message("Adding version file. Version: " + versionContent)
                    .content(versionContent)
                    .path(filePath)
                    .commit();
        } catch (IOException e2) {
            throw new IllegalStateException(e2);
        }
    }

    public GHPullRequest createPullRequest(String sourceBranch, String targetBranch, String version) {
        try {
            return repository.createPullRequest("Update version PR for version: " + version, sourceBranch,
                    targetBranch, "Update version PR for version: " + version);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

}
