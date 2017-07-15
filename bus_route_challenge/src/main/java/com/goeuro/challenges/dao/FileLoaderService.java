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
 * A file based implementation for {@link IDataLoaderService} DAO.
 * The service loads the bus routes from a file, given the data file path.
 * The file first line is the number of routes, and each subsequent line contains a space separated
 * list of integers. The first number is the route id, followed by the station ids.
 *
 * ### Example Data
 3
 0 0 1 2 3 4
 1 3 1 6 5
 2 0 6 4
 *
 * The data is loaded from the file into memory at application startup.
 * Any changes in the data file needs a restart in the system!
 * @see IDataLoaderService
 */
@Service
public class FileLoaderService implements  IDataLoaderService{

    private static Logger logger = LoggerFactory.getLogger(FileLoaderService.class);
    private Map<Integer, List<Integer>> busRoutes = new HashMap<>();

    @Override
    public Map<Integer, List<Integer>> getBusRoutes() {
        return busRoutes;
    }

    //TODO add file not found exception
    //loads the bus routes data file into a Map memory representation
    public void loadDataFile(String path) throws IOException {

        //read the lines, skip first line which contains number of routes
        //split each line with spaces and map to list of String,
        // then map to list of int,
        // Finally collect as a Map
        try (BufferedReader br = Files.newBufferedReader(Paths.get(path))) {
            busRoutes = br.lines()
                    .skip(1)
                    .map(line -> Arrays.asList(line.split(" ")))
                    .map(list -> toIntList(list))
                    .collect(Collectors.toMap(list -> list.get(0),
                            list -> list.subList(1, list.size())));
        }

        busRoutes.forEach((k,v) -> logger.trace("Bus route {} with stations {}", k, v));
    }

    //TODO refactor with Java8!!
    private List<Integer> toIntList(List<String> strList) {
        List<Integer> intList = new ArrayList<>();
        for(String s : strList) intList.add(Integer.valueOf(s));
        return intList;
    }
}
