package com.coderealms.gitopsactions.promote;

import java.util.List;

public class PromotionTarget {

    private List<String> targetEnvironments;
    private boolean autoApprove = false;

    public List<String> getTargetEnvironments() {
        return targetEnvironments;
    }

    public PromotionTarget setTargetEnvironments(List<String> targetEnvironments) {
        this.targetEnvironments = targetEnvironments;
        return this;
    }

    public boolean isAutoApprove() {
        return autoApprove;
    }

    public PromotionTarget setAutoApprove(boolean autoApprove) {
        this.autoApprove = autoApprove;
        return this;
    }
}
