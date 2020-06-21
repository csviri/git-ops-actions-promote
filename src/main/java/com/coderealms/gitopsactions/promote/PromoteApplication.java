package com.coderealms.gitopsactions.promote;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeExceptionMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.util.Arrays;

import static com.coderealms.gitopsactions.promote.ParamsUtils.toPropagationParams;


@SpringBootApplication
public class PromoteApplication implements CommandLineRunner {

    private final PromoteService promoteService;

    public PromoteApplication(PromoteService promoteService) {
        this.promoteService = promoteService;
    }

    public static void main(String[] args) {
        SpringApplication.run(PromoteApplication.class, args);
    }

    @Override
    public void run(String... args) {
        System.out.println(Arrays.toString(args));
        System.out.println(System.getenv().toString());
        File f = new File(".");
        System.out.println(Arrays.toString(f.list()));
        PromoteParams promoteParams = toPropagationParams(args);
        promoteService.propagateVersion(promoteParams);
    }

    @Bean
    ExitCodeExceptionMapper exitCodeToExceptionMapper() {
        return exception -> 1;
    }
}

