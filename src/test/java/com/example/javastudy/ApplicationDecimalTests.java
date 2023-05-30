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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ApplicationDecimalTests {

    @Autowired
    private MockMvc mock;
    
    @Test
    public void decimalNoInput() throws Exception {
        this.mock.perform(get("/decimal?value="))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"binForm\":" + "\"0\"")));
        ;
    }

    @Test
    public void decimalBadInput() throws Exception {
        final List<String> test = List.of("beep", "bop", "boop");
        for (var el : test) {
            this.mock.perform(get("/decimal?value=" + el))
                    .andExpect(status().isBadRequest());
        }
    }
    
    @Test
    public void decimalOutOfRangeInput() throws Exception {
        final List<Double> test = List.of(Double.NaN, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
        for (var el : test) {
            this.mock.perform(get("/decimal?value=" + el))
                    .andExpect(status().is5xxServerError());
        }
    }
    
    @Test
    public void decimalGoodInput() throws Exception {
        this.mock.perform(get("/decimal?value=" + "-78.99"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"binForm\":" + "\"-1001110.1111110101110000\"")));
    }

    @Test
    public void decimalBulkBadInput() throws Exception {
        this.mock.perform(post("/decimal?values=beep,bop,boop"))
                .andExpect(status().isBadRequest());
    }
    
    @Test
    public void decimalBulkOutOfRangeInput() throws Exception {
        this.mock.perform(post("/decimal?values=" + Double.NaN + "," + Double.NEGATIVE_INFINITY + "," + Double.POSITIVE_INFINITY))
                .andExpect(status().is5xxServerError());
    }

    @Test
    public void decimalBulkGoodInput() throws Exception {
        this.mock.perform(post("/decimal?values=97.66,-31.5,476"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"binForm\":" + "\"1100001.1010100011110101\"")))
                .andExpect(content().string(containsString("\"binForm\":" + "\"-11111.1\"")))
                .andExpect(content().string(containsString("\"binForm\":" + "\"111011100\"")))
                .andExpect(content().string(containsString("\"average\":180.72")))
                .andExpect(content().string(containsString("\"minimal\":-31.5")))
                .andExpect(content().string(containsString("\"maximal\":476")));
    }

    @Test
    public void asyncStatus() throws Exception {
        this.mock.perform(patch("/decimal?values=-94,78,13.333333"))
                .andExpect(status().isAccepted());
    }

    @Test
    public void asyncStatusFailed() throws Exception {
        this.mock.perform(patch("/decimal?values=not,really,values"))
                .andExpect(status().isBadRequest());
    }

}
