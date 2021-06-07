package com.creditsuisse.demo;

import com.creditsuisse.demo.model.EventDataJson;
import com.creditsuisse.demo.service.JsonReaderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    @Autowired
    private JsonReaderService jsonReaderService;

    static Logger logger = LoggerFactory.getLogger(DemoApplication.class);

    public static void main(String[] args) throws IOException {
        SpringApplication.run(DemoApplication.class, args);

    }

    public static String scanFileName() {
        logger.info("Scanning keyboard input");
        System.out.println("enter a file to scan");
        Scanner scanInput = new Scanner(System.in);
        String text = scanInput.nextLine();
        logger.info("successful keyboard input was : " + text);
        return text;
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("running application start");

        String textFileName = scanFileName();
        Reader reader = Files.newBufferedReader(Paths.get("src/main/resources/" + textFileName));
        logger.info("json file successfully parsed");

        List<EventDataJson> jsonReaderServicesList = jsonReaderService.readEventDataFromJson(reader);
        jsonReaderService.mappingToDatabase(jsonReaderServicesList);
        logger.info("mapping to database successfully");

    }
}
