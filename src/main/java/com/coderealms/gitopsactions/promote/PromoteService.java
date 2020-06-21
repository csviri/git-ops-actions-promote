package com.coderealms.gitopsactions.promote;

import org.kohsuke.github.GHPullRequest;
import org.kohsuke.github.GHRef;
import org.kohsuke.github.GitHubBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class PromoteService {

    private static final Logger log = LoggerFactory.getLogger(PromoteService.class);

    public void propagateVersion(PromoteParams promoteParams, PromotionMapping promotionMapping) {
        try {
            GitHubService gitHubService = new GitHubService(promoteParams.getToken(), promoteParams.getActualRepo());
            PromotionTarget promotionTarget = promotionMapping.getPromotionMapping().get(promoteParams.getActualBranch());
            for (String targetEnvironment : promotionTarget.getTargetEnvironments()) {
                String promoteText = "Promote environment from:" + promoteParams.getActualBranch()
                        + " to environment: " + targetEnvironment;
                log.info(promoteText);
                GHPullRequest pullRequest = gitHubService.createPullRequest(promoteParams.getActualBranch(), targetEnvironment, promoteText);
                if (promotionTarget.isAutoApprove()) {
                    log.info("Auto approving pull request");
                    pullRequest.merge("Auto-approving");
                }
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

}
