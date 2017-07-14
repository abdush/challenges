package com.goeuro.challenges.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by abdu on 7/13/2017.
 */
public class DirectBusRouteResponse {
    @JsonProperty("dep_sid")
    private int departStationId;
    @JsonProperty("arr_sid")
    private int arrivalStationId;
    @JsonProperty("direct_bus_route")
    private boolean directBusRoute;

    public DirectBusRouteResponse(int departStationId, int arrivalStationId, boolean directBusRoute) {
        this.departStationId = departStationId;
        this.arrivalStationId = arrivalStationId;
        this.directBusRoute = directBusRoute;
    }

    public int getDepartStationId() {
        return departStationId;
    }

    public void setDepartStationId(int departStationId) {
        this.departStationId = departStationId;
    }

    public int getArrivalStationId() {
        return arrivalStationId;
    }

    public void setArrivalStationId(int arrivalStationId) {
        this.arrivalStationId = arrivalStationId;
    }

    public boolean isDirectBusRoute() {
        return directBusRoute;
    }

    public void setDirectBusRoute(boolean directBusRoute) {
        this.directBusRoute = directBusRoute;
    }
}
