package com.coderealms.gitopsactions.promote;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeExceptionMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.io.IOException;

import static com.coderealms.gitopsactions.promote.ParamsUtils.fillPromoteParams;


@SpringBootApplication
public class PromoteApplication implements CommandLineRunner {

    public static final String GITOPS_ACTIONS_YAML_FILENAME = "gitopsactions.yaml";
    private final PromoteService promoteService;

    public PromoteApplication(PromoteService promoteService) {
        this.promoteService = promoteService;
    }

    public static void main(String[] args) {
        SpringApplication.run(PromoteApplication.class, args);
    }

    @Override
    public void run(String... args) throws IOException {
        PromoteParams promoteParams = fillPromoteParams(args);
        File mappingFile = new File(GITOPS_ACTIONS_YAML_FILENAME);
        PromotionMapping promotionMapping =
                ParamsUtils.parsePromotionMapping(FileUtils.readFileToString(mappingFile));
        promoteService.propagateVersion(promoteParams, promotionMapping);
    }


    @Bean
    ExitCodeExceptionMapper exitCodeToExceptionMapper() {
        return exception -> 1;
    }
}

