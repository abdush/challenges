package com.goeuro.challenges.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by abdu on 7/12/2017.
 */
@Service
public class BusRouteService implements IBusRouteService {

    private static Logger logger = LoggerFactory.getLogger(BusRouteService.class);

    @Override
    //checks for dept & arrival stations in all the given bus routes
    public boolean directRoute(Map<Integer, List<Integer>> busRoutes,
                               int depStationId, int arrStationId,
                               boolean biDirection) {

        boolean directRoute = false;
        for(List<Integer> busRoute: busRoutes.values()) {
            if(directRoute(busRoute, depStationId, arrStationId, biDirection)) {
                directRoute = true;
                break;
            }
        }
        return directRoute;
    }

    //checks for dept & arrival stations in the given bus route
    private boolean directRoute(List<Integer> busRoute,
                                int depStationId, int arrStationId,
                                boolean biDirection) {
        return biDirection ? findPointsBi(busRoute, depStationId, arrStationId) :
                findPointsUni(busRoute, depStationId, arrStationId);
    }

    //checks if nums List contains both start & end, and start comes before end
    private boolean findPointsUni(List<Integer> nums, int start, int end) {
        boolean foundStart = false;
        boolean foundEnd = false;

        for(int station: nums) {

            if(!foundStart) {
                if(start == station)
                    foundStart = true;
            } else {
                if(end == station) {
                    foundEnd = true;
                    break;
                }
            }
        }

        return foundEnd;
    }

    //checks if nums List contains both start & end, regardless of their position
    private boolean findPointsBi(List<Integer> nums, int start, int end) {
        boolean foundStart = false;
        boolean foundEnd = false;

        for(int num: nums) {

            if(!foundStart && start == num)
                foundStart = true;

            if(!foundEnd && end == num)
                foundEnd = true;

            if(foundStart && foundEnd)
                return true;
        }

        return false;
    }
}
