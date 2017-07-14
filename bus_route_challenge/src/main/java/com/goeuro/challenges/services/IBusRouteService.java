package com.goeuro.challenges.services;

import java.util.List;
import java.util.Map;

/**
 * Created by abdu on 7/14/2017.
 */
public interface IBusRouteService {

    boolean directRoute(Map<Integer, List<Integer>> busRoutes,
                        int depStationId, int arrStationId,
                        boolean biDirection);

    default boolean directRoute(Map<Integer, List<Integer>> busRoutes,
                                int depStationId, int arrStationId) {
        return directRoute(busRoutes, depStationId, arrStationId, true);
    }
}
