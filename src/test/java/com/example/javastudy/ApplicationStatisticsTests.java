package com.example.javastudy;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ApplicationStatisticsTests {
    
    @Autowired
    private MockMvc mock;

    @Test
    public void getNullStat() throws Exception {
        this.mock.perform(get("/stat"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"count\":" + "0")));
    }

    @Test
    public void getUsageStat() throws Exception {
        final List<String> test = List.of("-71", "3.666", "99999");
        for (var el : test) {
            this.mock.perform(get("/decimal?value=" + el))
                    .andExpect(status().isOk());
        }
        this.mock.perform(post("/decimal?values=96.3,2,-7.99"))
                .andExpect(status().isOk());
        this.mock.perform(get("/stat"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"count\":" + "4")));
    }

}
