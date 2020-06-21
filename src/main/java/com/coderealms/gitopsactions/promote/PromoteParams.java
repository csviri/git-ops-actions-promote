package com.coderealms.gitopsactions.promote;

public class PromoteParams {

    private String token;
    private String actualRepo;
    private String actualBranch;

    public String getActualBranch() {
        return actualBranch;
    }

    public PromoteParams setActualBranch(String actualBranch) {
        this.actualBranch = actualBranch;
        return this;
    }

    public String getToken() {
        return token;
    }

    public PromoteParams setToken(String token) {
        this.token = token;
        return this;
    }

    public String getActualRepo() {
        return actualRepo;
    }

    public PromoteParams setActualRepo(String actualRepo) {
        this.actualRepo = actualRepo;
        return this;
    }
}
