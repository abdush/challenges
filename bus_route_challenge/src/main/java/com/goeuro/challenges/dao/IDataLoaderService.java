package com.goeuro.challenges.dao;

import java.util.List;
import java.util.Map;

/**
 * Created by abdu on 7/14/2017.
 * Abstraction for data access layer.
 * The service returns the bus routes represented as a {@link Map}.
 * Each entry represents a single bus route.
 * The key is the unique route integer id,
 * and the value is the list of station integer ids the route connects.
 */
public interface IDataLoaderService {

    Map<Integer, List<Integer>> getBusRoutes();
}
