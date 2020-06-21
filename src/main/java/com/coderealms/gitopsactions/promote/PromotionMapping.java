package com.coderealms.gitopsactions.promote;

import java.util.Map;

public class PromotionMapping {

    private Map<String,PromotionTarget> promotionMapping;

    public Map<String, PromotionTarget> getPromotionMapping() {
        return promotionMapping;
    }

    public PromotionMapping setPromotionMapping(Map<String, PromotionTarget> promotionMapping) {
        this.promotionMapping = promotionMapping;
        return this;
    }
}
