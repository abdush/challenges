package com.goeuro.challenges.services;

import java.util.List;
import java.util.Map;

/**
 * Created by abdu on 7/14/2017.
 */
public interface IBusRouteService {

    /**
     * checks if there is a direct route between the specified departure
     * and arrival stations, in all the given bus routes.
     * @param busRoutes all the bus routes defined as a Map.
     *                  The map key is the route id and the value is a list of the stations (ids) the route connects.
     * @param depStationId the departure station id
     * @param arrStationId the arrival station id
     * @param biDirection whether the bus routes are bidirectional, (moves in both directions)
     * @return
     */
    boolean directRoute(Map<Integer, List<Integer>> busRoutes,
                        int depStationId, int arrStationId,
                        boolean biDirection);

    default boolean directRoute(Map<Integer, List<Integer>> busRoutes,
                                int depStationId, int arrStationId) {
        return directRoute(busRoutes, depStationId, arrStationId, true);
    }
}
