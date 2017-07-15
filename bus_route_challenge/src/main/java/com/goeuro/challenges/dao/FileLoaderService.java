package com.goeuro.challenges.dao;

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
public class FileLoaderService implements  IDataLoaderService{

    private static Logger logger = LoggerFactory.getLogger(FileLoaderService.class);
    private List<String> lines = new ArrayList<>();
    private Map<Integer, List<Integer>> busRoutes = new HashMap<>();

    @Override
    public Map<Integer, List<Integer>> getBusRoutes() {
        return busRoutes;
    }

    //TODO add file not found exception
    public void loadDataFile(String path) {

        try (BufferedReader br = Files.newBufferedReader(Paths.get(path))) {
            lines = br.lines().collect(Collectors.toList());
        } catch (IOException e) {
            logger.warn("Error reading file {}", e);
        }

        lines.remove(0);
        //lines.forEach(System.out::println);
        for(String line: lines) {
            List<Integer> busRoute = parseLine(line);
            busRoutes.put(busRoute.get(0),busRoute.subList(1, busRoute.size()));
        }
        for(Map.Entry<Integer, List<Integer>> key: busRoutes.entrySet() ) {
            logger.trace("Bus route {} with stations {}", key.getKey(), key.getValue());
        }
    }

    private List<Integer> parseLine(String line) {
        String[] items = line.split(" ");
        List<String> strList = Arrays.asList(items);
        List<Integer> intList = new ArrayList<>();
        for(String s : strList) intList.add(Integer.valueOf(s));
        return intList;
    }

}
