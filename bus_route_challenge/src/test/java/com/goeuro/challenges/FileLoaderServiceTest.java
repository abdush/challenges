package com.goeuro.challenges;

import com.goeuro.challenges.dao.FileLoaderService;
import com.goeuro.challenges.dao.IDataLoaderService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by abdu on 7/15/2017.
 */
@RunWith(SpringRunner.class)
public class FileLoaderServiceTest {

    @TestConfiguration
    static class FileLoaderServiceImplTestContextConfiguration {

        @Bean
        public IDataLoaderService fileLoaderService() {
            return new FileLoaderService();
        }
    }

    @Autowired
    FileLoaderService fileLoaderService;

    @Before
    public void setup() throws IOException {
        String path = "src\\test\\resources\\data\\example2";
        fileLoaderService.loadDataFile(path);
    }

    @Test
    public void loadDataFile() {
        Map<Integer, List<Integer>> busRoutes = fileLoaderService.getBusRoutes();
        assertThat(busRoutes)
                .isNotEmpty()
                .hasSize(10)
                .containsOnlyKeys(1,2,19,13,14,6,7,8,18,11)
                .containsEntry(2, Arrays.asList(5,142,106,11));
    }
}
