package com.goeuro.challenges;

/**
 * Created by abdu on 7/15/2017.
 */
/*
@RunWith(SpringRunner.class)
@SpringBootTest
public class BusRouteControllerIntegrationTest {

    @Autowired
    ApplicationContext ctx;

    @Autowired
    private MockMvc mvc;
    //TODO
    // This fails because no command-line args are passed
    @Test
    public void busRouteJsonResponse() throws Exception {

        mvc.perform(get("/api/direct").param("dep_sid","3").param("arr_sid","6"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.dep_sid", is(3) ))
                .andExpect(jsonPath("$.arr_sid", is(6) ))
                .andExpect(jsonPath("$.direct_bus_route", is(true) ));


    }

}
*/