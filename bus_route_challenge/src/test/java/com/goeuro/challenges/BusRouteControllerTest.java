package com.goeuro.challenges;

import com.goeuro.challenges.dao.FileLoaderService;
import com.goeuro.challenges.rest.BusRouteController;
import com.goeuro.challenges.services.IBusRouteService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
/**
 * Created by abdu on 7/15/2017.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(BusRouteController.class)
public class BusRouteControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private FileLoaderService fileLoaderService;

    @MockBean
    private IBusRouteService busRouteService;

    @MockBean
    private Application application;

    Map<Integer, List<Integer>> busRoutes = new HashMap<>();

    @Before
    public void setup() {
        busRoutes.put(0, Arrays.asList(0,1,2,3,4));
        busRoutes.put(1, Arrays.asList(3,1,6,5));
        busRoutes.put(2, Arrays.asList(0,6,4));
        given(fileLoaderService.getBusRoutes()).willReturn(busRoutes);
    }

    @Test
    public void busRouteJsonResponse() throws Exception {
        given(busRouteService.directRoute(busRoutes, 3,6)).willReturn(true);
        mvc.perform(get("/api/direct").param("dep_sid","3").param("arr_sid","6"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.dep_sid", is(3) ))
                .andExpect(jsonPath("$.arr_sid", is(6) ))
                .andExpect(jsonPath("$.direct_bus_route", is(true) ));

    }
}
