package com.coderealms.gitopsactions.promote;

public class ParamsUtils {

    public static PromoteParams toPropagationParams(String[] args) {
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

        promoteParams.setActualBranch(System.getProperty("GITHUB_REF"));
        promoteParams.setActualRepo(System.getProperty("GITHUB_REPOSITORY_OWNER") + "/" + System.getProperty("GITHUB_REPOSITORY"));


        return promoteParams;
    }

}
