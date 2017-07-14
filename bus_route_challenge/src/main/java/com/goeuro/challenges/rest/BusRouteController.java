package com.goeuro.challenges.rest;

import com.goeuro.challenges.model.DirectBusRouteResponse;
import com.goeuro.challenges.services.BusRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by abdu on 7/13/2017.
 */
@RestController
@RequestMapping("api")
public class BusRouteController {

    @Autowired
    private BusRouteService busRouteService;

    @RequestMapping(value = "/direct", produces="application/json", method = RequestMethod.GET)
    public ResponseEntity<DirectBusRouteResponse> directRoute(
            @RequestParam(value = "dep_sid", required = true) int dep_id,
            @RequestParam(value = "arr_sid", required = true) int arr_id) {

        return new ResponseEntity<>(busRouteService.directRoute(dep_id, arr_id), HttpStatus.OK);
    }
}
