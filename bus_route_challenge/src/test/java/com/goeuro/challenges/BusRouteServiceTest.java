package com.goeuro.challenges;

import com.goeuro.challenges.services.BusRouteService;
import com.goeuro.challenges.services.IBusRouteService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by abdu on 7/15/2017.
 */
@RunWith(SpringRunner.class)
public class BusRouteServiceTest {

    @TestConfiguration
    static class BusRouteServiceImplTestContextConfiguration {

        @Bean
        public IBusRouteService busRouteService() {
            return new BusRouteService();
        }
    }

    @Autowired
    IBusRouteService busRouteService;

    Map<Integer, List<Integer>> busRoutes = new HashMap<>();

    @Before
    public void setup() {
        busRoutes.put(0, Arrays.asList(0,1,2,3,4));
        busRoutes.put(1, Arrays.asList(3,1,6,5));
        busRoutes.put(2, Arrays.asList(0,6,4));
    }

    @Test
    public void directRouteExists() {
        boolean directRoute = busRouteService.directRoute(busRoutes,3,6);
        assertThat(directRoute).isTrue();
    }

    @Test
    public void directRouteBiDirectionExists() {
        boolean directRoute = busRouteService.directRoute(busRoutes,6,3);
        assertThat(directRoute).isTrue();
    }

    @Test
    public void directRouteNotExists() {
        boolean directRoute = busRouteService.directRoute(busRoutes,5,2);
        assertThat(directRoute).isFalse();
    }

    @Test
    public void directRouteUniDirectionNotExists() {
        boolean directRoute = busRouteService.directRoute(busRoutes,6,3, false);
        assertThat(directRoute).isFalse();
    }
}
