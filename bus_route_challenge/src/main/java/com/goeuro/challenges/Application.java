package com.goeuro.challenges;

import com.goeuro.challenges.dao.FileLoaderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.Arrays;

/**
 * Created by abdu on 7/12/2017.
 */
@SpringBootApplication
public class Application implements ApplicationRunner {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);
    @Autowired
    FileLoaderService fileLoaderService;

    public static void main(String... args) {
        if(args.length != 1)
            usage();
        SpringApplication.run(Application.class, args);
    }

    private static void usage() {
        logger.error("System exit. Usage: java -jar <jarfile.jar> <filepath>");
        System.exit(-1);
    }

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        logger.info("Application commandline args... {}" , Arrays.toString(applicationArguments.getSourceArgs()));
        try {
            fileLoaderService.loadDataFile(applicationArguments.getSourceArgs()[0]);
        } catch (IOException e) {
            logger.error("System exit. Couldn't Open File {}" , applicationArguments.getSourceArgs()[0]);
            System.exit(-1);
        }
    }
}
