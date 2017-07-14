package com.goeuro.challenges.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by abdu on 7/12/2017.
 */
@Service
public class FileLoaderService {

    private static Logger logger = LoggerFactory.getLogger(FileLoaderService.class);

    private List<String> lines = new ArrayList<>();
    private Map<Integer, List<Integer>> busRoutes = new HashMap<>();

    public void readFile(String path) {

        logger.info("Reading File");
        try (BufferedReader br = Files.newBufferedReader(Paths.get(path))) {
            lines = br.lines().collect(Collectors.toList());
        } catch (IOException e) {
            logger.warn("Error reading file {}", e);
        }

        lines.forEach(System.out::println);
        lines = lines.subList(1, lines.size());
        for(String line: lines) {
            List<Integer> busRoute = parseLine(line);
            busRoutes.put(busRoute.get(0),busRoute.subList(1, busRoute.size()));
        }
        for(Map.Entry<Integer, List<Integer>> key: busRoutes.entrySet() ) {
            logger.trace("Bus route {} with stations {}", key.getKey(), key.getValue());
        }
    }

    public List<Integer> parseLine(String line) {
        String[] items = line.split(" ");
        List<String> strList = Arrays.asList(items);
        List<Integer> intList = new ArrayList<>();
        for(String s : strList) intList.add(Integer.valueOf(s));
        return intList;
    }

    public List<String> getLines() {
        return lines;
    }

    public Map<Integer, List<Integer>> getBusRoutes() {
        return busRoutes;
    }
}
