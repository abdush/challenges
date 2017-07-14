package com.goeuro.challenges.services;

import com.goeuro.challenges.model.DirectBusRouteResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by abdu on 7/12/2017.
 */
@Service
public class BusRouteService {

    private static Logger logger = LoggerFactory.getLogger(BusRouteService.class);

    @Autowired
    FileLoaderService fileLoaderService;

    public DirectBusRouteResponse directRoute(int depStationId, int arrStationId) {
        boolean directRoute = false;
        for(List<Integer> busRoute: fileLoaderService.getBusRoutes().values()) {
            if(directRoute(busRoute, depStationId, arrStationId)) {
                directRoute = true;
                break;
            }
        }
        return new DirectBusRouteResponse(depStationId, arrStationId, directRoute);
    }

    public boolean directRoute(List<Integer> busRoute, int start, int end) {
        return directRoute(busRoute, start, end, true);
    }

    public boolean directRoute(List<Integer> busRoute, int start, int end, boolean biDirection) {
        return biDirection ? findPointsBi(busRoute, start, end) : findPointsUni(busRoute, start, end);
    }

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
