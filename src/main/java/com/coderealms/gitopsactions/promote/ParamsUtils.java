package com.coderealms.gitopsactions.promote;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

public class ParamsUtils {

    public static PromoteParams fillPromoteParams(String[] args) {
        PromoteParams promoteParams = new PromoteParams();
        for (String arg : args) {
            int delimiterIndex = arg.indexOf("=");
            String key = arg.substring(0, delimiterIndex);
            String value = arg.substring(delimiterIndex + 1);
            switch (key) {
                case "token":
                    promoteParams.setToken(value);
                    break;
                default:
                    throw new IllegalArgumentException("Unknows param key:" + key);
            }
        }
        System.out.println("GITHUB REF: "+System.getProperty("GITHUB_REF"));
        promoteParams.setActualBranch(System.getProperty("GITHUB_REF").replace("refs/heads/", ""));
        promoteParams.setActualRepo(System.getProperty("GITHUB_REPOSITORY"));
        return promoteParams;
    }

    public static PromotionMapping parsePromotionMapping(String mapping) {
        try {
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            return mapper.readValue(mapping, PromotionMapping.class);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException(e);
        }
    }

}
