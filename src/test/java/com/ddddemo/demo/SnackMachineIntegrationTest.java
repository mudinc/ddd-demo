package com.ddddemo.demo;


import java.util.Optional;

import com.ddddemo.demo.models.SnackMachine;
import com.ddddemo.demo.services.SnackMachineService;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.SeedStrategy;
import com.github.database.rider.junit5.DBUnitExtension;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;
import com.github.database.rider.core.api.connection.ConnectionHolder;

import java.time.LocalDateTime; // Import the LocalDateTime class
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter; // Import the DateTimeFormatter class
import java.util.Date;

import javax.sql.DataSource;

@ExtendWith({DBUnitExtension.class, SpringExtension.class})
@SpringBootTest
@ActiveProfiles("test")
@RunWith(JUnit4.class)
@AutoConfigureMockMvc
class SnackMachineIntegrationTest{
    
    
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private DataSource dataSource;
    public ConnectionHolder getConnectionHolder() {
        // Return a function that retrieves a connection from our data source
        return () -> dataSource.getConnection();
    }

    @Test
    @DataSet(value = "datasets/machinesandsnacks.yml",strategy = SeedStrategy.CLEAN_INSERT)
    @DisplayName("GET /snackmachines/1 - FOUND")
    void testGetSnackMachineById() throws Exception{      

        mockMvc.perform(get("/api/snackmachines/{1}",1))
            .andExpect(status().isOk())
            .andExpect(content().contentType(
                MediaType.APPLICATION_JSON_VALUE
            ))
            .andExpect(header().string(HttpHeaders.ETAG,"\"1\""))
            .andExpect(jsonPath("$.id",is(1)))
            .andExpect(jsonPath("$.machine_name",is("Machine 1")));
    

    }

    @Test
    @DataSet(value = "datasets/machinesandsnacks.yml",strategy = SeedStrategy.CLEAN_INSERT)
    @DisplayName("POST /snackmachines - CREATE")
    void testCreateSnackMachine() throws Exception{      
        SnackMachine newMachine=new SnackMachine();
        newMachine.setId(Long.valueOf(3));
        newMachine.setMachine_name("Machine 3");
        newMachine.setMachine_description("machine_description");
        newMachine.setVersion("3");

        Date in = new Date();
        LocalDateTime ldt = LocalDateTime.ofInstant(in.toInstant(), ZoneId.systemDefault());
        Date out = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
        newMachine.setCreatedAt(out);
        newMachine.setUpdatedAt(out);

        mockMvc.perform(post("/api/snackmachines")
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(newMachine)))
            .andExpect(status().isCreated())
            .andExpect(content().contentType(
                MediaType.APPLICATION_JSON_VALUE
            ))
            .andExpect(header().string(HttpHeaders.ETAG,"\"3\""))
            .andExpect(jsonPath("$.id",is(3)))
            .andExpect(jsonPath("$.machine_name",is("Machine 3")));
    

    }

    static String asJsonString(final Object obj){
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
