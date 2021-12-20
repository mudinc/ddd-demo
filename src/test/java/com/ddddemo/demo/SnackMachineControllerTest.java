package com.ddddemo.demo;


import java.util.Optional;

import com.ddddemo.demo.models.SnackMachine;
import com.ddddemo.demo.services.SnackMachineService;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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


import java.time.LocalDateTime; // Import the LocalDateTime class
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter; // Import the DateTimeFormatter class
import java.util.Date;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class SnackMachineControllerTest{
    
    @MockBean
    private SnackMachineService service;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("GET /snackmachines/1 - FOUND")
    void testGetSnackMachineById() throws Exception{
        assertTrue("Hello".equals("Hello"));
        

        Date in = new Date();
        LocalDateTime ldt = LocalDateTime.ofInstant(in.toInstant(), ZoneId.systemDefault());
        Date out = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());

        SnackMachine mockMachine=new SnackMachine();

        mockMachine.setCreatedAt(out);
        mockMachine.setUpdatedAt(out);
        mockMachine.setId(Long.valueOf(1));
        mockMachine.setMachine_name("machine_name");
        mockMachine.setVersion("1");

        doReturn(Optional.of(mockMachine)).when(service).findById(Long.valueOf(1));


        mockMvc.perform(get("/api/snackmachines/{1}",1))
            .andExpect(status().isOk())
            .andExpect(content().contentType(
                MediaType.APPLICATION_JSON_VALUE
            ))
            .andExpect(header().string(HttpHeaders.ETAG,"\"1\""))
            .andExpect(jsonPath("$.id",is(1)))
            .andExpect(jsonPath("$.machine_name",is("machine_name")));
    

    }
}
